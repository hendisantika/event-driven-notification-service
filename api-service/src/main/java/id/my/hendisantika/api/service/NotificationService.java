package id.my.hendisantika.api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import id.my.hendisantika.api.repository.NotificationRepository;
import id.my.hendisantika.notificationservice.shared.domain.SendNotificationRequest;
import id.my.hendisantika.notificationservice.shared.domain.SendNotificationResponse;
import id.my.hendisantika.notificationservice.shared.dto.NotificationState;
import id.my.hendisantika.notificationservice.shared.entity.Notification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;

/**
 * Created by IntelliJ IDEA.
 * Project : event-driven-notification-service
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 11/03/26
 * Time: 06.01
 * To change this template use File | Settings | File Templates.
 */
@Service
public class NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);
    private static final String RATE_LIMIT_KEY_PREFIX = "rate_limit:";
    private static final Duration RATE_LIMIT_WINDOW = Duration.ofMinutes(1);
    private final NotificationRepository notificationRepository;
    private final RedisTemplate<String, Object> redisStreamTemplate;
    private final String streamName;
    private final int rateLimitPerUserPerMinute;
    private final ObjectMapper objectMapper;

    public NotificationService(NotificationRepository notificationRepository,
                               @Qualifier("redisStreamTemplate") RedisTemplate<String, Object> redisStreamTemplate,
                               @Qualifier("notificationStreamName") String streamName,
                               @Value("${notification.rate-limit.per-user-per-minute:10}") int rateLimitPerUserPerMinute,
                               ObjectMapper objectMapper) {
        this.notificationRepository = notificationRepository;
        this.redisStreamTemplate = redisStreamTemplate;
        this.streamName = streamName;
        this.rateLimitPerUserPerMinute = rateLimitPerUserPerMinute;
        this.objectMapper = objectMapper;
    }

    /**
     * Accept notification request. Idempotent: same idempotencyKey returns same response.
     * Returns 202 Accepted with notification ID.
     */
    @Transactional
    public SendNotificationResponse send(SendNotificationRequest request) {
        // 1. Idempotency check: if key exists, return existing (exactly-once guarantee)
        var existing = notificationRepository.findByIdempotencyKey(request.getIdempotencyKey());
        if (existing.isPresent()) {
            logger.info("Idempotency hit: idempotencyKey={}, notificationId={}", request.getIdempotencyKey(), existing.get().getId());
            return new SendNotificationResponse(existing.get().getId(), existing.get().getState().name());
        }

        // 2. Rate limit: per-user per minute
        if (!checkRateLimit(request.getUserId())) {
            throw new RateLimitExceededException("Rate limit exceeded for user " + request.getUserId());
        }

        // 3. Persist notification with PENDING state
        var notification = new Notification();
        notification.setIdempotencyKey(request.getIdempotencyKey());
        notification.setUserId(request.getUserId());
        notification.setChannel(request.getChannel());
        notification.setPayload(request.getPayload());
        notification.setState(NotificationState.PENDING);
        notification.setRetryCount(0);

        try {
            notification = notificationRepository.save(notification);
        } catch (DataIntegrityViolationException e) {
            // Unique constraint on idempotency_key: concurrent duplicate, return existing
            var dup = notificationRepository.findByIdempotencyKey(request.getIdempotencyKey());
            if (dup.isPresent()) {
                return new SendNotificationResponse(dup.get().getId(), dup.get().getState().name());
            }
            throw e;
        }

        // 4. Increment rate limit counter
        incrementRateLimit(request.getUserId());

        // 5. Publish event to Redis Stream
        publishToStream(notification);

        logger.info("Notification accepted: id={}, userId={}, channel={}", notification.getId(), notification.getUserId(), notification.getChannel());
        return new SendNotificationResponse(notification.getId(), NotificationState.PENDING.name());
    }
}
