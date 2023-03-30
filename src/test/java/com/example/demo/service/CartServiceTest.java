package com.example.demo.service;

import com.example.demo.constant.ProductSellStatus;
import com.example.demo.dto.CartProductDto;
import com.example.demo.entity.CartProduct;
import com.example.demo.entity.Member;
import com.example.demo.entity.Product;
import com.example.demo.repository.CartProductRepository;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@TestPropertySource(locations="classpath:application-test.properties")
class CartServiceTest {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    CartService cartService;

    @Autowired
    CartProductRepository cartProductRepository;

    public Product saveProduct() {
        Product product = new Product();
        product.setProductName("테스트 상품");
        product.setPrice(10000);
        product.setOrigin("국내산");
        product.setSeller("판매자");
        product.setGuide("테스트 상품 상세 설명");
        product.setProductSellStatus(ProductSellStatus.SELL);
        product.setStockNumber(100);
        return productRepository.save(product);
    }

    public Member saveMember(){
        Member member = new Member();
        member.setEmail("test@test.com");
        return memberRepository.save(member);
    }

    @Test
    @DisplayName("장바구니 담기 테스트")
    public void addCart(){
        Product product = saveProduct();
        Member member = saveMember();

        CartProductDto cartProductDto = new CartProductDto();
        cartProductDto.setCount(5);
        cartProductDto.setProductId(product.getId());

        Long cartProductId = cartService.addCart(cartProductDto, member.getEmail());
        CartProduct cartProduct = cartProductRepository.findById(cartProductId)
                .orElseThrow(EntityNotFoundException::new);

        assertEquals(product.getId(), cartProduct.getProduct().getId());
        assertEquals(cartProductDto.getCount(), cartProduct.getCount());
    }
}