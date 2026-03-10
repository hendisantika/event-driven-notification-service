package id.my.hendisantika.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by IntelliJ IDEA.
 * Project : event-driven-notification-service
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 11/03/26
 * Time: 05.57
 * To change this template use File | Settings | File Templates.
 */
@SpringBootApplication(scanBasePackages = "id.my.hendisantika.notificationservice")
@EntityScan(basePackages = "id.my.hendisantika.shared.entity")
@EnableJpaRepositories(basePackages = "id.my.hendisantika.api.repository")
public class ApiServiceApplication {

    static void main(String[] args) {
        SpringApplication.run(ApiServiceApplication.class, args);
    }
}