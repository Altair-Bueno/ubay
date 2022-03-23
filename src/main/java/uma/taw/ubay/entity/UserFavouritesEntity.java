package uma.taw.ubay.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "user_favourites", schema = "public")
@IdClass(UserFavouritesEntityPK.class)
public class UserFavouritesEntity {
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryEntity category;
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
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

    public void setUser(ClientEntity user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserFavouritesEntity)) return false;
        UserFavouritesEntity that = (UserFavouritesEntity) o;
        return Objects.equals(category, that.category) && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(category, user);
    }
}
