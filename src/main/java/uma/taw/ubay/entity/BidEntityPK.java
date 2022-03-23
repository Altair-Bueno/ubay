package uma.taw.ubay.entity;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;
import java.util.Objects;

public class BidEntityPK implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private ClientEntity user;

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
        if (!(o instanceof BidEntityPK)) return false;
        BidEntityPK that = (BidEntityPK) o;
        return Objects.equals(product, that.product) && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, user);
    }
}
