package com.example.commerce.serviece;

import com.example.commerce.dto.ProductDto;
import com.example.commerce.model.ProductParam;

import java.util.List;

public interface ProductService{
    List<ProductDto> list(ProductParam parameter);
}
