package uma.taw.ubay.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "bid", schema = "public")
@IdClass(BidEntityPK.class)
public class BidEntity {
    @Basic
    @Column(name = "publish_date", nullable = false)
    private Timestamp publishDate;
    @Basic
    @Column(name = "amount", nullable = false, precision = 0)
    private double amount;
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private ClientEntity user;

    public Timestamp getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Timestamp publishDate) {
        this.publishDate = publishDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
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
        if (!(o instanceof BidEntity)) return false;
        BidEntity bidEntity = (BidEntity) o;
        return Double.compare(bidEntity.amount, amount) == 0 && Objects.equals(publishDate, bidEntity.publishDate) && Objects.equals(product, bidEntity.product) && Objects.equals(user, bidEntity.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(publishDate, amount, product, user);
    }
}
