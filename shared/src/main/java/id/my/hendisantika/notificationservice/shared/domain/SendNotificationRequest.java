package id.my.hendisantika.notificationservice.shared.domain;

import id.my.hendisantika.notificationservice.shared.dto.NotificationChannel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * Project : event-driven-notification-service
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 11/03/26
 * Time: 05.34
 * To change this template use File | Settings | File Templates.
 */
public class SendNotificationRequest {

    @NotBlank(message = "userId is required")
    @Size(max = 255)
    private String userId;

    @NotNull(message = "channel is required")
    private NotificationChannel channel;

    @NotNull(message = "payload is required")
    private Map<String, Object> payload;

    @NotBlank(message = "idempotencyKey is required")
    @Size(max = 255)
    private String idempotencyKey;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public NotificationChannel getChannel() {
        return channel;
    }

    public void setChannel(NotificationChannel channel) {
        this.channel = channel;
    }

    public Map<String, Object> getPayload() {
        return payload;
    }

    public void setPayload(Map<String, Object> payload) {
        this.payload = payload;
    }

    public String getIdempotencyKey() {
        return idempotencyKey;
    }

    public void setIdempotencyKey(String idempotencyKey) {
        this.idempotencyKey = idempotencyKey;
    }
}
