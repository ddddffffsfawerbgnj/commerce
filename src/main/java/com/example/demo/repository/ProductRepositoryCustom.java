package com.example.demo.repository;

import com.example.demo.dto.ProductSearchDto;
import com.example.demo.entity.Product;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;

public interface ProductRepositoryCustom {

    Page<Product> getAdminItemPage(ProductSearchDto productSearchDto,
                                   Pageable pageable);

//    Page<MainD> getMainItemPage(ItemSearchDto itemSearchDto,
//                                   Pageable pageable);

}
