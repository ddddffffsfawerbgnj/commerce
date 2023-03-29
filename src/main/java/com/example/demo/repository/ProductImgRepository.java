package com.example.demo.repository;

import com.example.demo.entity.ProductImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductImgRepository extends JpaRepository<ProductImg, Long> {

    List<ProductImg> findByProductIdOrderByIdAsc(Long productId);

}
