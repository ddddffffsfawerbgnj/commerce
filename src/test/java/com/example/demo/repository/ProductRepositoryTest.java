package com.example.demo.repository;

import com.example.demo.constant.ProductSellStatus;
import com.example.demo.entity.Product;
import com.example.demo.entity.QProduct;
import com.querydsl.core.BooleanBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    @DisplayName("상품 저장 테스트")
    public void createProductTest() {
        Product product = new Product();
        product.setProductName("테스트 상품");
        product.setPrice(10000);
        product.setSeller("은수");
        product.setOrigin("국내산");
        product.setGuide("테스트 상품 상세 설명");
        product.setStockNumber(10);
        product.setProductSellStatus(ProductSellStatus.SELL);
        product.setRegTime(LocalDateTime.now());
        product.setUpdateTime(LocalDateTime.now());
        Product savedProduct = productRepository.save(product);
        System.out.println(savedProduct.toString());
    }

    public void createProductList() {
        for (int i = 1; i <= 10; i++) {
            Product product = new Product();
            product.setProductName("테스트 상품" + i);
            product.setPrice(10000 + i);
            product.setSeller("판매자" + i);
            product.setOrigin("국내산" + i);
            product.setGuide("테스트 상품 상세 설명" + i);
            product.setStockNumber(10);
            product.setProductSellStatus(ProductSellStatus.SELL);
            product.setRegTime(LocalDateTime.now());
            product.setUpdateTime(LocalDateTime.now());
            Product savedProduct = productRepository.save(product);
        }
    }

    public void createProductList2() {
        for (int i = 1; i <= 5; i++) {
            Product product = new Product();
            product.setProductName("테스트 상품" + i);
            product.setPrice(10000 + i);
            product.setSeller("판매자" + i);
            product.setOrigin("국내산" + i);
            product.setGuide("테스트 상품 상세 설명" + i);
            product.setStockNumber(10);
            product.setProductSellStatus(ProductSellStatus.SELL);
            product.setRegTime(LocalDateTime.now());
            product.setUpdateTime(LocalDateTime.now());
            productRepository.save(product);
        }

        for (int i = 6; i <= 10; i++) {
            Product product = new Product();
            product.setProductName("테스트 상품" + i);
            product.setPrice(10000 + i);
            product.setSeller("판매자" + i);
            product.setOrigin("국내산" + i);
            product.setGuide("테스트 상품 상세 설명" + i);
            product.setStockNumber(10);
            product.setProductSellStatus(ProductSellStatus.SELL);
            product.setRegTime(LocalDateTime.now());
            product.setUpdateTime(LocalDateTime.now());
            productRepository.save(product);
        }
    }

    @Test
    @DisplayName("상품 Querydsl1 조회 테스트 2")
    public void queryDslTest2() {

        this.createProductList2();

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        QProduct product = QProduct.product;
        String guide = "테스트 상품 상세 설명";
        int price = 10003;
        String productSellStatus = "SELL";

        booleanBuilder.and(product.guide.like("%" + guide + "%"));
        booleanBuilder.and(product.price.gt(price));

        if (StringUtils.equals(productSellStatus, ProductSellStatus.SELL)) {
            booleanBuilder.and(product.productSellStatus.eq(ProductSellStatus.SELL));
        }

        Pageable pageable = PageRequest.of(0, 5);
        Page<Product> productPagingResult =
                productRepository.findAll(booleanBuilder, pageable);
        System.out.println("total elements: " + productPagingResult.getTotalElements());

        List<Product> resultProductList = productPagingResult.getContent();
        for (Product resultProduct: resultProductList) {
            System.out.println(resultProduct.toString());
        }

    }

}