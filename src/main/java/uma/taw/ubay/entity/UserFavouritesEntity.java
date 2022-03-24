package uma.taw.ubay.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "user_favourites", schema = "public")
public class UserFavouritesEntity {
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @EmbeddedId
    UserFavouritesEntityPK key;

    @ManyToOne
    @MapsId("category")
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryEntity category;
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @MapsId("user")
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private ClientEntity user;

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public ClientEntity getUser() {
        return user;
    }

    public UserFavouritesEntityPK getKey() {
        return key;
    }

    public void setKey(UserFavouritesEntityPK key) {
        this.key = key;
    }

    public void setUser(ClientEntity user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserFavouritesEntity)) return false;
        UserFavouritesEntity that = (UserFavouritesEntity) o;
        return Objects.equals(key, that.key) && Objects.equals(category, that.category) && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, category, user);
    }
}
