package com.example.commerce.repository;

import com.example.commerce.dto.ProductDto;
import com.example.commerce.entity.Member;
import com.example.commerce.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {

}
