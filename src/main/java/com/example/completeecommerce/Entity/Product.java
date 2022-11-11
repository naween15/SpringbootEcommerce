package com.example.completeecommerce.Entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.repository.cdi.Eager;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long productId;

    private String productName;
    @Column(length = 2000)
    private String productDescription;
    private int productDiscountedPrice;
    private int productActualPrice;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "PRODUCTANDIMAGES",
                      joinColumns={
            @JoinColumn(name = "product_id")
                      },
            inverseJoinColumns = {
            @JoinColumn(name = "image_id")
            }
    )
    private Set<ProductImages> productImages;

}
