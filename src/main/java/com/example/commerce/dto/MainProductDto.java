package com.example.commerce.dto;

import com.example.commerce.entity.Product;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MainProductDto {

    private Long id;
    private String productName;
    private String productDetail;
    private String imgUrl;
    private Integer price;

    //추가컬럼
    long totalCount;
    long seq;

    public MainProductDto(Long id, String productName, String productDetail,
                          String imgUrl, Integer price) {
        this.id = id;
        this.productName = productName;
        this.productDetail = productDetail;
        this.imgUrl = imgUrl;
        this.price = price;
    }

}
