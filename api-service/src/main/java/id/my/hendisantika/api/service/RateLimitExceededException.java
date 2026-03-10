package id.my.hendisantika.api.service;

import org.springframework.http.HttpStatus;

/**
 * Created by IntelliJ IDEA.
 * Project : event-driven-notification-service
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 11/03/26
 * Time: 06.03
 * To change this template use File | Settings | File Templates.
 */
public class RateLimitExceededException extends RuntimeException {

    private final HttpStatus status = HttpStatus.TOO_MANY_REQUESTS;

    public RateLimitExceededException(String message) {
        super(message);
    }

    public HttpStatus getStatus() {
        return status;
    }
}
