package com.example.commerce.dto;

import com.example.commerce.constant.ProductSellStatus;
import com.example.commerce.entity.Product;
import com.example.commerce.entity.ProductImg;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import javax.mail.FetchProfile;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Data
public class ProductFormDto {
    private String productId;

    @NotBlank(message = "상품명은 필수 입력 값입니다.")
    private String productName;
    @NotBlank(message = "가격은 필수 입력 값입니다.")
    private String price;
    @NotBlank(message = "판매자는 필수 입력 값입니다.")
    private String seller;
    @NotBlank(message = "원산지는 필수 입력 값입니다.")
    private String origin;
    @NotBlank(message = "안내사항은 필수 입력 값입니다.")
    private String guide;
    @NotNull(message = "재고는 필수 입력 값입니다.")
    private Integer stockNumber;
    private ProductSellStatus productSellStatus;

    private List<ProductDto> productDtoList = new ArrayList<>();
    private List<Long> productImgIds = new ArrayList<>();

    private static ModelMapper modelMapper = new ModelMapper();

    public Product createProduct() {
        return modelMapper.map(this, Product.class);
    }

    public static ProductFormDto of(Product product) {
        return modelMapper.map(product, ProductFormDto.class);
    }

}
