package com.example.commerce.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "product_img")
public class ProductImg extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_img_id")
    private Long productImgId;

    private String imgName; // 이미지 파일명

    private String oriImgName; //원본 이미지 파일명

    private String imgUrl; // 이미지 조회 경로

    private String repImgYn; // 대표 이지미 여부

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    public void updateProductImg(String oriImgName, String imgName,
                                 String imgUrl){
        this.oriImgName = oriImgName;
        this.imgName = imgName;
        this.imgUrl = imgUrl;
    }
}
