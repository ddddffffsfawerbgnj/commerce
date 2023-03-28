package com.example.demo.dto;

import com.example.demo.constant.ProductSellStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ProductDto {

    private Long id;

    private String productName;

    private Integer price;

    private String seller;

    private String origin;

    private String guide;

    private int stockNumber;

    private ProductSellStatus productSellStatus;

    private LocalDateTime regTime;

    private LocalDateTime updateTime;
}
