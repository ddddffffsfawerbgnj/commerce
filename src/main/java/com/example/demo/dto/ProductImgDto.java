package com.example.demo.dto;

import com.example.demo.entity.ProductImg;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter
@Setter
public class ProductImgDto {

    private Long id;

    private String imgName;

    private String oriImgName;

    private String repImgYn;

    private static ModelMapper modelMapper = new ModelMapper();

    public static ProductImgDto of(ProductImg productImg) {
        return modelMapper.map(productImg, ProductImgDto.class);
    }

}
