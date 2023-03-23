package com.example.commerce.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Product {
    @Id
    private Long productId;

    private String productName;
    private int price;
    private String seller;
    private String origin;
    private String guide;
    private int stockNumber;


    String filename;
    String urlFilename;

}
