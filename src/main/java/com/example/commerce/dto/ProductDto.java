package com.example.commerce.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {
    private String productId;

    private String productName;
    private String price;
    private String seller;
    private String origin;
    private String guide;

    //추가컬럼
    long totalCount;
    long seq;
}
