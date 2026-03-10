package id.my.hendisantika.notificationservice.worker.repository;

import id.my.hendisantika.notificationservice.shared.entity.DeadLetterNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by IntelliJ IDEA.
 * Project : event-driven-notification-service
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 11/03/26
 * Time: 05.47
 * To change this template use File | Settings | File Templates.
 */
@Repository
public interface DeadLetterNotificationRepository extends JpaRepository<DeadLetterNotification, Long> {
}
