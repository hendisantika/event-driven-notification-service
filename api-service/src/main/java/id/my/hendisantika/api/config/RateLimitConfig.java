package id.my.hendisantika.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Created by IntelliJ IDEA.
 * Project : event-driven-notification-service
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 11/03/26
 * Time: 05.59
 * To change this template use File | Settings | File Templates.
 */
@Configuration
public class RateLimitConfig {

    @Value("${notification.rate-limit.per-user-per-minute:10}")
    private int perUserPerMinute;

    public int getPerUserPerMinute() {
        return perUserPerMinute;
    }

    public void setPerUserPerMinute(int perUserPerMinute) {
        this.perUserPerMinute = perUserPerMinute;
    }
}
