package id.my.hendisantika.api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import id.my.hendisantika.api.repository.NotificationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

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
}
