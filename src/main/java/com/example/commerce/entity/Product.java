package com.example.commerce.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Product {
    @Id
    private String productId;

    private String productName;
    private String price;
    private String seller;
    private String origin;
    private String guide;

}
