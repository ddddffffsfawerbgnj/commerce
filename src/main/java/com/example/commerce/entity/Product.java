package com.example.commerce.entity;

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


}
