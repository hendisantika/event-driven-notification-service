package id.my.hendisantika.notificationservice.worker.service;

/**
 * Created by IntelliJ IDEA.
 * Project : event-driven-notification-service
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 11/03/26
 * Time: 05.49
 * To change this template use File | Settings | File Templates.
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import id.my.hendisantika.notificationservice.worker.repository.DeadLetterNotificationRepository;
import id.my.hendisantika.notificationservice.worker.repository.NotificationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Handles notification delivery, state transitions, and DLQ on permanent failure.
 */
@Service
public class NotificationDeliveryService {

    private static final Logger logger = LoggerFactory.getLogger(NotificationDeliveryService.class);

    private final NotificationRepository notificationRepository;
    private final DeadLetterNotificationRepository deadLetterRepository;
    private final MockNotificationProvider mockProvider;
    private final ObjectMapper objectMapper;
    private final int maxRetries;

    public NotificationDeliveryService(NotificationRepository notificationRepository,
                                       DeadLetterNotificationRepository deadLetterRepository,
                                       MockNotificationProvider mockProvider,
                                       ObjectMapper objectMapper,
                                       @Value("${notification.max-retries:5}") int maxRetries) {
        this.notificationRepository = notificationRepository;
        this.deadLetterRepository = deadLetterRepository;
        this.mockProvider = mockProvider;
        this.objectMapper = objectMapper;
        this.maxRetries = maxRetries;
    }
}
