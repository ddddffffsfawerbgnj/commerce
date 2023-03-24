package com.example.commerce.dto;

import com.example.commerce.constant.ProductSellStatus;
import com.example.commerce.entity.Product;
import com.example.commerce.entity.ProductImg;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ProductDto {
    private Long productId;

    @NotBlank(message = "상품명은 필수 입력 값입니다.")
    private String productName;
    @NotNull(message = "가격은 필수 입력 값입니다.")
    private Integer price;
    @NotBlank(message = "판매자는 필수 입력 값입니다.")
    private String seller;
    @NotBlank(message = "원산지는 필수 입력 값입니다.")
    private String origin;
    @NotBlank(message = "상품 상세 내용은 필수 입력 값입니다.")
    private String guide;
    @NotNull(message = "재고는 필수 입력 값입니다.")
    private Integer stockNumber;
    private ProductSellStatus productSellStatus;

    //추가컬럼
    long totalCount;
    long seq;

    private List<ProductImgDto> productImgDtoList = new ArrayList<>();
    private List<Long> productImgIds = new ArrayList<>();

    private static ModelMapper modelMapper = new ModelMapper();

    public Product createProduct() {
        return modelMapper.map(this, Product.class);
    }

    public static ProductDto of(Product product) {
        return modelMapper.map(product, ProductDto.class);
    }

}
