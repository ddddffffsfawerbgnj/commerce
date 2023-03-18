package com.example.commerce.dto;

import com.example.commerce.entity.ProductImg;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

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

    private static ModelMapper modelMapper = new ModelMapper();

    public static ProductDto of(ProductImg productImg){
        return modelMapper.map(productImg, ProductDto.class);
    }

    /* 상품상세 */
    public ProductDto(String productId, String productName,
                              String price,
                          String seller, String origin, String guide){
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.seller = seller;
        this.origin = origin;
        this.guide = guide;
    }
}
