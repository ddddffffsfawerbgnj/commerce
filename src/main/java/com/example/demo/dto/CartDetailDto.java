package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CartDetailDto {

    private Long cartProductId; //장바구니 상품 아이디

    private String productName; //상품명

    private int price; //상품 금액

    private int count; //수량

    private String imgUrl; //상품 이미지 경로

    public CartDetailDto(Long cartProductId, String productName, int price, int count,
                         String imgUrl){
        this.cartProductId = cartProductId;
        this.productName = productName;
        this.price = price;
        this.count = count;
        this.imgUrl = imgUrl;
    }

}