package com.example.commerce.serviece.impl;

import com.example.commerce.dto.ProductDto;
import com.example.commerce.entity.Product;
import com.example.commerce.mapper.ProductMapper;
import com.example.commerce.model.ProductParam;
import com.example.commerce.repository.ProductRepository;
import com.example.commerce.serviece.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productMapper;
    private final ProductRepository productRepository;

    /* 상품목록 */
    @Override
    public List<ProductDto> list(ProductParam parameter) {
        long totalCount = productMapper.selectListCount(parameter);

        List<ProductDto> list = productMapper.selectList(parameter);
        if (!CollectionUtils.isEmpty(list)) {
            int i = 0;
            for (ProductDto x : list) {
                x.setTotalCount(totalCount);
                x.setSeq(totalCount - parameter.getPageStart() - i);
                i++;
            }
        }

        return list;
    }

    /* 상품상세 */
    @Override
    public ProductDto detail(String productId) {
        return null;
    }

}
