package id.my.hendisantika.api.controller;

/**
 * Created by IntelliJ IDEA.
 * Project : event-driven-notification-service
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 11/03/26
 * Time: 06.04
 * To change this template use File | Settings | File Templates.
 */

import id.my.hendisantika.api.service.NotificationService;
import id.my.hendisantika.notificationservice.shared.domain.SendNotificationRequest;
import id.my.hendisantika.notificationservice.shared.domain.SendNotificationResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST API for notification acceptance. Returns 202 Accepted immediately; delivery is asynchronous.
 */
@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private static final Logger log = LoggerFactory.getLogger(NotificationController.class);

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    /**
     * Accept notification request. Idempotent via Idempotency-Key header or body.
     * Returns 202 Accepted with notification ID.
     */
    @PostMapping("/send")
    public ResponseEntity<SendNotificationResponse> send(@Valid @RequestBody SendNotificationRequest request,
                                                         @RequestHeader(value = "Idempotency-Key", required = false) String headerKey) {
        // Allow idempotency key from header (preferred) or body
        if (headerKey != null && !headerKey.isBlank()) {
            request.setIdempotencyKey(headerKey.trim());
        }
        SendNotificationResponse response = notificationService.send(request);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

}
