package entities;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    Long productId;

    @Column(name = "name")
    String name;

    @Column(name = "indications")
    String indications;

    @Column(name = "manufacturer_info")
    String manufacturerInfo;

    @Column(name = "side_effects")
    String sideEffects;

    @Column(name = "quantity")
    int quantity;

    @Column(name = "price")
    int price;

    @Column(name = "prescribed")
    Boolean prescribed;

    @Column(name = "image")
    Byte image;
//    ByteArray image;


    public Product() {
    }

    public Product(String name, String indications, String manufacturerInfo, String sideEffects,
                   int quantity, int price, Boolean prescribed, Byte image) {
        this.name = name;
        this.indications = indications;
        this.manufacturerInfo = manufacturerInfo;
        this.sideEffects = sideEffects;
        this.quantity = quantity;
        this.price = price;
        this.prescribed = prescribed;
        this.image = image;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIndications() {
        return indications;
    }

    public void setIndications(String indications) {
        this.indications = indications;
    }

    public String getManufacturerInfo() {
        return manufacturerInfo;
    }

    public void setManufacturerInfo(String manufacturerInfo) {
        this.manufacturerInfo = manufacturerInfo;
    }

    public String getSideEffects() {
        return sideEffects;
    }

    public void setSideEffects(String sideEffects) {
        this.sideEffects = sideEffects;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Boolean getPrescribed() {
        return prescribed;
    }

    public void setPrescribed(Boolean prescribed) {
        this.prescribed = prescribed;
    }

    public Byte getImage() {
        return image;
    }

    public void setImage(Byte image) {
        this.image = image;
    }
}
