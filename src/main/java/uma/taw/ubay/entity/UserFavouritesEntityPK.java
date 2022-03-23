package uma.taw.ubay.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserFavouritesEntityPK implements Serializable {
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", nullable = false)
    private int category;
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private int user;

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserFavouritesEntityPK)) return false;
        UserFavouritesEntityPK that = (UserFavouritesEntityPK) o;
        return category == that.category && user == that.user;
    }

    @Override
    public int hashCode() {
        return Objects.hash(category, user);
    }
}
