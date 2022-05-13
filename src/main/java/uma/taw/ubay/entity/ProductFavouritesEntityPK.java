package uma.taw.ubay.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;
/**
 * @author Altair Bueno
 */

@Embeddable
public class ProductFavouritesEntityPK implements Serializable {
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false)
    private int product;
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private int user;

    public int getProduct() {
        return product;
    }

    public void setProduct(int product) {
        this.product = product;
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
        if (!(o instanceof ProductFavouritesEntityPK)) return false;
        ProductFavouritesEntityPK that = (ProductFavouritesEntityPK) o;
        return product == that.product && user == that.user;
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, user);
    }
}
