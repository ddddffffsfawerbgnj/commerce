package com.example.commerce.entity;

import com.example.commerce.constant.ProductSellStatus;
import com.example.commerce.dto.ProductDto;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Table(name = "product")
@ToString
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    private Long productId;

    private String productName;
    private Integer price;
    private String seller;
    private String origin;
    private String guide;
    private Integer stockNumber;

    @Enumerated(EnumType.STRING)
    private ProductSellStatus productSellStatus;


    public void updateProduct(ProductDto productDto) {
        this.productName = productDto.getProductName();
        this.price = productDto.getPrice();
        this.seller = productDto.getSeller();
        this.origin = productDto.getOrigin();
        this.guide = productDto.getGuide();
        this.stockNumber = productDto.getStockNumber();
        this.productSellStatus = productDto.getProductSellStatus();
    }

}
