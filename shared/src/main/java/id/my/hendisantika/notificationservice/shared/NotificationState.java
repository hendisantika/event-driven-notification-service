package id.my.hendisantika.notificationservice.shared;

/**
 * Created by IntelliJ IDEA.
 * Project : event-driven-notification-service
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 11/03/26
 * Time: 05.33
 * To change this template use File | Settings | File Templates.
 */

/**
 * State machine for notification lifecycle.
 * PENDING -> PROCESSING -> SENT
 * \  -> RETRYING -> (retry) -> PROCESSING
 * \              -> (max retries) -> FAILED
 * \-> FAILED (permanent error)
 */
public enum NotificationState {
    PENDING,
    PROCESSING,
    SENT,
    RETRYING,
    FAILED
}
