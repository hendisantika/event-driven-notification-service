package id.my.hendisantika.notificationservice.worker.service;

/**
 * Created by IntelliJ IDEA.
 * Project : event-driven-notification-service
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 11/03/26
 * Time: 05.51
 * To change this template use File | Settings | File Templates.
 */

/**
 * Transient failure: timeout, 5xx, connection reset. Safe to retry.
 */
public class TransientDeliveryException extends RuntimeException {

    public TransientDeliveryException(String message) {
        super(message);
    }

    public TransientDeliveryException(String message, Throwable cause) {
        super(message, cause);
    }
}
