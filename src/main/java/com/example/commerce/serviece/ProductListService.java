package com.example.commerce.serviece;

import com.example.commerce.dto.ProductDto;
import com.example.commerce.model.ProductParam;

import java.util.List;

public interface ProductListService{

    List<ProductDto> list(ProductParam parameter);

    /* 상품목록 - 구매자 */
    List<ProductDto> productList(ProductParam parameter);
}
