package code.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @SequenceGenerator(name = "my_seq", sequenceName = "my_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_seq")
    @Column(name = "product_id")
    private Long productId;

    @NotNull
    @Column(name = "name")
    private String name;

    @Column(name = "indications")
    private String indications;

    @Column(name = "manufacturer_info")
    private String manufacturerInfo;

    @Column(name = "side_effects")
    private String sideEffects;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private int price;

    @Column(name = "prescribed")
    private Boolean prescribed;

    @Column(name = "image")
    private Byte image;
//    ByteArray image;

}
