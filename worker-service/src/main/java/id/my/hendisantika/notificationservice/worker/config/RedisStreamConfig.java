package id.my.hendisantika.notificationservice.worker.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Created by IntelliJ IDEA.
 * Project : event-driven-notification-service
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 11/03/26
 * Time: 05.41
 * To change this template use File | Settings | File Templates.
 */
@Configuration
public class RedisStreamConfig {

    @Value("${notification.stream.name:notifications:stream}")
    private String streamName;

    @Value("${notification.stream.consumer-group:delivery-workers}")
    private String consumerGroup;

    @Value("${notification.stream.consumer-name:worker-1}")
    private String consumerName;

    @Bean
    public RedisTemplate<String, Object> redisStreamTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new StringRedisSerializer());
        template.afterPropertiesSet();
        return template;
    }

    @Bean
    public String notificationStreamName() {
        return streamName;
    }

    @Bean
    public String consumerGroup() {
        return consumerGroup;
    }

    @Bean
    public String consumerName() {
        return consumerName;
    }
}