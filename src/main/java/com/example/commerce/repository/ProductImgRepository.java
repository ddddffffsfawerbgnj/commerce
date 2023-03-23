package com.example.commerce.repository;

import com.example.commerce.entity.ProductImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductImgRepository extends JpaRepository<ProductImg, Long> {
    List<ProductImg> findByProductImgIdOrderByProductImgIdAsc(Long productImgId);
}
