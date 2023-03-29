package com.example.demo.dto;

import com.example.demo.constant.ProductSellStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductSearchDto {

    private String searchDateType;

    private ProductSellStatus searchSellStatus;

    private String searchBy;

    private String searchQuery = "";

}
