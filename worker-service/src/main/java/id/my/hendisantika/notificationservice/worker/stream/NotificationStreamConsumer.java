package id.my.hendisantika.notificationservice.worker.stream;

/**
 * Created by IntelliJ IDEA.
 * Project : event-driven-notification-service
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 11/03/26
 * Time: 05.42
 * To change this template use File | Settings | File Templates.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;

/**
 * Consumes notifications from Redis Stream using consumer group.
 * Creates stream and group on startup if missing.
 * Processes messages and ACKs on success or permanent failure.
 */
@Component
public class NotificationStreamConsumer implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(NotificationStreamConsumer.class);
    private static final Duration POLL_TIMEOUT = Duration.ofSeconds(5);
    private static final int BATCH_SIZE = 10;
    private final String streamName;
    private final String consumerGroup;
    private final String consumerName;
    private final NotificationDeliveryService deliveryService;
    private final RedisConnectionFactory connectionFactory;
    private final RedisTemplate<String, Object> redisTemplate;

    public NotificationStreamConsumer(@Qualifier("notificationStreamName") String streamName,
                                      @Qualifier("consumerGroup") String consumerGroup,
                                      @Qualifier("consumerName") String consumerName,
                                      NotificationDeliveryService deliveryService,
                                      RedisConnectionFactory connectionFactory,
                                      @Qualifier("redisStreamTemplate") RedisTemplate<String, Object> redisTemplate) {
        this.streamName = streamName;
        this.consumerGroup = consumerGroup;
        this.consumerName = consumerName;
        this.deliveryService = deliveryService;
        this.connectionFactory = connectionFactory;
        this.redisTemplate = redisTemplate;
    }

}
