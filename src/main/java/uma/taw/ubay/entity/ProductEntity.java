package uma.taw.ubay.entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "product", schema = "public")
public class ProductEntity {
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_id_generator")
    @SequenceGenerator(name = "product_id_generator", sequenceName = "product_id_seq", allocationSize = 1)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "title", nullable = false, length = 50)
    private String title;
    @Basic
    @Column(name = "description", nullable = false, length = 250)
    private String description;
    @Basic
    @Column(name = "out_price", nullable = false, precision = 0)
    private double outPrice;
    @Basic
    @Column(name = "images", nullable = true, length = 100)
    private String images;
    @Basic
    @Column(name = "close_date", nullable = true)
    private Date closeDate;
    @Basic
    @Column(name = "publish_date", nullable = false)
    private Timestamp publishDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendor_id", nullable = false)
    private ClientEntity vendor;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryEntity category;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getOutPrice() {
        return outPrice;
    }

    public void setOutPrice(double outPrice) {
        this.outPrice = outPrice;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public Date getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    }

    public Timestamp getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Timestamp publishDate) {
        this.publishDate = publishDate;
    }

    public ClientEntity getVendor() {
        return vendor;
    }

    public void setVendor(ClientEntity vendor) {
        this.vendor = vendor;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public boolean isCurrentlyAvailable() {
        return this.getCloseDate() == null || this.getCloseDate().after(new java.util.Date());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductEntity)) return false;
        ProductEntity that = (ProductEntity) o;
        return id == that.id && Double.compare(that.outPrice, outPrice) == 0 && Objects.equals(title, that.title) && Objects.equals(description, that.description) && Objects.equals(images, that.images) && Objects.equals(closeDate, that.closeDate) && Objects.equals(publishDate, that.publishDate) && Objects.equals(vendor, that.vendor) && Objects.equals(category, that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, outPrice, images, closeDate, publishDate, vendor, category);
    }
}
