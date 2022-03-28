package uma.taw.ubay.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "login_credentials", schema = "public")
public class LoginCredentialsEntity {
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "username", nullable = false, length = 20)
    private String username;
    @Basic
    @Column(name = "password", nullable = false, length = 20)
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(name = "kind",nullable = false)
    private KindEnum kind;
    @OneToOne
    @JoinColumn(name = "user_id", nullable = true)
    private ClientEntity user;

    public LoginCredentialsEntity() {}

    public LoginCredentialsEntity(String username, String password, KindEnum kind, ClientEntity user) {
        super();
        this.username = username;
        this.password = password;
        this.kind = kind;
        this.user = user;
    }

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

    public KindEnum getKind() {
        return kind;
    }

    public void setKind(KindEnum kind) {
        this.kind = kind;
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
        LoginCredentialsEntity entity = (LoginCredentialsEntity) o;
        return Objects.equals(username, entity.username) && Objects.equals(password, entity.password) && kind == entity.kind && Objects.equals(user, entity.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, kind, user);
    }
}
