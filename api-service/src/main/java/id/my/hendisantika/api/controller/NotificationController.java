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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
