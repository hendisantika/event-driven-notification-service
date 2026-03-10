package id.my.hendisantika.notificationservice.shared.domain;

/**
 * Created by IntelliJ IDEA.
 * Project : event-driven-notification-service
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 11/03/26
 * Time: 05.35
 * To change this template use File | Settings | File Templates.
 */
public class SendNotificationResponse {
    private Long notificationId;
    private String state;

    public SendNotificationResponse(Long notificationId, String state) {
        this.notificationId = notificationId;
        this.state = state;
    }

    public Long getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Long notificationId) {
        this.notificationId = notificationId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
