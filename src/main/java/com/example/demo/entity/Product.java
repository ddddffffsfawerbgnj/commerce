package com.example.demo.entity;

import com.example.demo.constant.ProductSellStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity
@Table(name = "product")
public class Product {

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 50)
    private String productName;

    @Column(name = "price", nullable = false)
    private int price;

    @Column(name = "seller", nullable = false)
    private String seller;

    @Column(name = "origin", nullable = false)
    private String origin;

    @Column(name = "guide", nullable = false)
    private String guide;

    @Column(name = "stockNumber")
    private int stockNumber;

    @Enumerated(EnumType.STRING)
    private ProductSellStatus productSellStatus;

    private LocalDateTime regTime;

    private LocalDateTime updateTime;

}