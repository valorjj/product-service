package com.example.productservice.entity;

import com.example.productservice.common.BaseTime;
import com.example.productservice.model.ProductRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "PRODUCT_TBL")
@NoArgsConstructor(access = PROTECTED)
@Getter
@AllArgsConstructor
public class Product extends BaseTime {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "PRODUCT_ID")
    private Long id;

    @Column(name = "PRODUCT_NAME")
    private String name;

    @Column(name = "PRODUCT_PRICE")
    private Long price;

    @Column(name = "PRODUCT_QUANTITY")
    private Long quantity;

    public void reduceProductQuantity(Long quantity) {
        this.quantity -= quantity;
    }

    public Product(ProductRequest productRequest) {
        this.name = productRequest.productName();
        this.price = productRequest.price();
        this.quantity = productRequest.quantity();
    }

}
