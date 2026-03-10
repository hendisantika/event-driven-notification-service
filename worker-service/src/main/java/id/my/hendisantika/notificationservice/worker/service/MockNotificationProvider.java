package id.my.hendisantika.notificationservice.worker.service;

/**
 * Created by IntelliJ IDEA.
 * Project : event-driven-notification-service
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 11/03/26
 * Time: 05.48
 * To change this template use File | Settings | File Templates.
 */

import id.my.hendisantika.notificationservice.shared.dto.NotificationChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Mock notification provider for EMAIL and SMS.
 * In production, replace with real providers (SendGrid, Twilio, etc.).
 */
@Service
public class MockNotificationProvider {

    private static final Logger logger = LoggerFactory.getLogger(MockNotificationProvider.class);

    /**
     * Simulate sending notification. Returns success or throws for failure.
     * For testing: pass payload with "fail" or "failTransient" to simulate failures.
     */
    public void send(NotificationChannel channel, Map<String, Object> payload) {
        logger.info("Mock provider sending: channel={}, payload={}", channel, payload);

        // Simulate transient failure (e.g. timeout, 503)
        if (payload != null && "failTransient".equals(payload.get("simulate"))) {
            throw new TransientDeliveryException("Simulated transient failure");
        }

        // Simulate permanent failure (e.g. 400, invalid address)
        if (payload != null && "failPermanent".equals(payload.get("simulate"))) {
            throw new PermanentDeliveryException("Simulated permanent failure: invalid payload");
        }

        // Success
        logger.info("Mock provider sent successfully");
    }
}
