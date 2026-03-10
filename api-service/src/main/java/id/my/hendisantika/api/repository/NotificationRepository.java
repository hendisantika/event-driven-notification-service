package id.my.hendisantika.api.repository;

import id.my.hendisantika.notificationservice.shared.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * Project : event-driven-notification-service
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 11/03/26
 * Time: 06.01
 * To change this template use File | Settings | File Templates.
 */
@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    /**
     * Idempotency lookup: if key exists, return existing notification (exactly-once guarantee).
     */
    Optional<Notification> findByIdempotencyKey(String idempotencyKey);
}
