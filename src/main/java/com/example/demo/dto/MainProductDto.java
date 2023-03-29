package com.example.demo.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MainProductDto {

    private Long id;

    private String productName;

    private String guide;

    private String imgUrl;

    private Integer price;

    @QueryProjection
    public MainProductDto(Long id, String productName, String guide,
                          String imgUrl, Integer price){
        this.id = id;
        this.productName = productName;
        this.guide = guide;
        this.imgUrl = imgUrl;
        this.price = price;

    }
}
