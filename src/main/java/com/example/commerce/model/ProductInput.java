package com.example.commerce.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductInput {

    private Long productId;
    private String productName;
    private int price;
    private String seller;
    private String origin;
    private String guide;
    private int stockNumber;

    //삭제를 위한
    String idList;

    //ADD
    String filename;
    String urlFilename;

}
