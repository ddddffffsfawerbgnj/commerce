package com.example.commerce.mapper;

import com.example.commerce.dto.ProductDto;
import com.example.commerce.model.ProductParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {

    long selectListCount(ProductParam parameter);
    List<ProductDto> selectList(ProductParam parameter);

}
