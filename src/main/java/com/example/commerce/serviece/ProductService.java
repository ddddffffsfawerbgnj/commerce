package com.example.commerce.serviece;

import com.example.commerce.dto.ProductDto;
import com.example.commerce.model.ProductParam;

import java.util.List;

public interface ProductService{

    /* 상품목록 */
    List<ProductDto> list(ProductParam parameter);

    /* 상품상세 */
    ProductDto detail(String productId);
}
