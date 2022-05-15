package uma.taw.ubay.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;
/**
 * @author Altair Bueno
 */

@Embeddable
public class PasswordResetEntityPK implements Serializable {
    @Column(name = "username", nullable = false, length = 20)
    private String username;
    @Column(name = "request_id", nullable = false, length = 20, insertable = false, updatable = false)
    private String requestId;

    public PasswordResetEntityPK() {
    }

    public PasswordResetEntityPK(String username, String requestId) {
        this.username = username;
        this.requestId = requestId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PasswordResetEntityPK)) return false;
        PasswordResetEntityPK that = (PasswordResetEntityPK) o;
        return Objects.equals(username, that.username) && Objects.equals(requestId, that.requestId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, requestId);
    }
}
