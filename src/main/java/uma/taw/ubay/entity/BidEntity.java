package uma.taw.ubay.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "bid", schema = "public")
@IdClass(BidEntityPK.class)
public class BidEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "bid_id_generator")
    @SequenceGenerator(name = "bid_id_generator",sequenceName = "bid_id_seq",allocationSize = 1)
    @Column(name = "id", nullable = false, updatable = false)
    private int id;
    @Basic
    @Column(name = "publish_date", nullable = false)
    private Timestamp publishDate;
    @Basic
    @Column(name = "amount", nullable = false, precision = 0)
    private double amount;
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private ClientEntity user;

    public BidEntity() {
    }

    public BidEntity(Timestamp publishDate, double amount, ProductEntity product, ClientEntity user) {
        this.publishDate = publishDate;
        this.amount = amount;
        this.product = product;
        this.user = user;
    }

    public Timestamp getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Timestamp publishDate) {
        this.publishDate = publishDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return id == bidEntity.id && Double.compare(bidEntity.amount, amount) == 0 && Objects.equals(publishDate, bidEntity.publishDate) && Objects.equals(product, bidEntity.product) && Objects.equals(user, bidEntity.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, publishDate, amount, product, user);
    }
}
