package uma.taw.ubay.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "login_credentials", schema = "public")
public class LoginCredentialsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "username", nullable = false, length = 20)
    private String username;
    @Basic
    @Column(name = "password", nullable = false, length = 20)
    private String password;
    @OneToOne
    @JoinColumn(name = "user_id", nullable = true)
    private ClientEntity user;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ClientEntity getUser() {
        return user;
    }

    public void setUser(ClientEntity user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LoginCredentialsEntity)) return false;
        LoginCredentialsEntity that = (LoginCredentialsEntity) o;
        return Objects.equals(username, that.username) && Objects.equals(password, that.password) && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, user);
    }
}
