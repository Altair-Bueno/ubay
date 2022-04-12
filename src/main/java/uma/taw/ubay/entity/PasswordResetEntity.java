package uma.taw.ubay.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "password_reset", schema = "public")
public class PasswordResetEntity {
    @EmbeddedId
    PasswordResetEntityPK key;

    @ManyToOne
    @MapsId("username")
    @JoinColumn(name = "username",nullable = false)
    private LoginCredentialsEntity user;
    @Basic
    @MapsId("requestId")
    @Column(name = "request_id",nullable = false,length = 20)
    private String requestId;

    public PasswordResetEntityPK getKey() {
        return key;
    }

    public void setKey(PasswordResetEntityPK key) {
        this.key = key;
    }

    public LoginCredentialsEntity getUser() {
        return user;
    }

    public void setUser(LoginCredentialsEntity user) {
        this.user = user;
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
        if (!(o instanceof PasswordResetEntity)) return false;
        PasswordResetEntity that = (PasswordResetEntity) o;
        return Objects.equals(key, that.key) && Objects.equals(user, that.user) && Objects.equals(requestId, that.requestId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, user, requestId);
    }
}
