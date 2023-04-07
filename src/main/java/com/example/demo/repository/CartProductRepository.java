package com.example.demo.repository;

import com.example.demo.dto.CartDetailDto;
import com.example.demo.entity.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartProductRepository extends JpaRepository<CartProduct, Long> {

    CartProduct findByCart_IdAndProductId(Long cartId, Long productId);

    @Query("select new com.example.demo.dto.CartDetailDto(ci.id, i " +
            ".productName, i " +
            ".price, ci " +
            ".count, im.imgUrl) " +
            "from CartProduct ci, ProductImg im " +
            "join ci.product i " +
            "where ci.cart.id = :cartId " +
            "and im.product.id = ci.product.id " +
            "and im.repImgYn = 'Y' " +
            "order by ci.regTime desc "
    )
    List<CartDetailDto> findCartDetailDtoList(Long cartId);
}
