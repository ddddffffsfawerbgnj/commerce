package com.example.demo.dto;

import com.example.demo.constant.ProductSellStatus;
import com.example.demo.entity.Product;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.ui.Model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ProductFormDto {

    private Long id;

    @NotBlank(message = "상품명은 필수 입력 값입니다.")
    private String productName;

    @NotNull(message = "가격은 필수 입력 값입니다.")
    private Integer price;

    @NotBlank(message = "판매자는 필수 입력 값입니다.")
    private String seller;

    @NotBlank(message = "원산지는 필수 입력 값입니다.")
    private String origin;

    @NotBlank(message = "상세설명은 필수 입력 값입니다.")
    private String guide;

    @NotNull(message = "재고는 필수 입력 값입니다.")
    private Integer stockNumber;

    private ProductSellStatus productSellStatus;

    private List<ProductImgDto> productImgDtoList = new ArrayList<>();

    private List<Long> productImgIds = new ArrayList<>();

    private static ModelMapper modelMapper = new ModelMapper();

    public Product createProduct() {
        return modelMapper.map(this, Product.class);
    }

    public static ProductFormDto of(Product product) {
        return modelMapper.map(product, ProductFormDto.class);
    }

}
