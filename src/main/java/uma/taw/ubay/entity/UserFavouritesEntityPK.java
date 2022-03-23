package uma.taw.ubay.entity;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;
import java.util.Objects;

public class UserFavouritesEntityPK implements Serializable {
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
        if (!(o instanceof UserFavouritesEntityPK)) return false;
        UserFavouritesEntityPK that = (UserFavouritesEntityPK) o;
        return Objects.equals(category, that.category) && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(category, user);
    }
}
