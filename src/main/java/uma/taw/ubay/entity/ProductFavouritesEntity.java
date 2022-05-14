package uma.taw.ubay.entity;

import jakarta.persistence.*;

import java.util.Objects;
/**
 * @author Altair Bueno
 */

@Entity
@Table(name = "product_favourites", schema = "public")
public class ProductFavouritesEntity {
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @EmbeddedId
    ProductFavouritesEntityPK key;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("product")
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @MapsId("user")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private ClientEntity user;

    public ProductFavouritesEntityPK getKey() {
        return key;
    }

    public void setKey(ProductFavouritesEntityPK key) {
        this.key = key;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
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
        if (!(o instanceof ProductFavouritesEntity)) return false;
        ProductFavouritesEntity that = (ProductFavouritesEntity) o;
        return Objects.equals(key, that.key) && Objects.equals(product, that.product) && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, product, user);
    }
}
