package com.example.demo.service;

import com.example.demo.dto.CartDetailDto;
import com.example.demo.dto.CartProductDto;
import com.example.demo.entity.Cart;
import com.example.demo.entity.CartProduct;
import com.example.demo.entity.Member;
import com.example.demo.entity.Product;
import com.example.demo.repository.CartProductRepository;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {

    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;
    private final CartRepository cartRepository;
    private final CartProductRepository cartProductRepository;

    public Long addCart(CartProductDto cartProductDto, String email) {
        Product product = productRepository.findById(cartProductDto.getProductId())
                .orElseThrow(EntityNotFoundException::new);
        Member member = memberRepository.findByEmail(email);

        Cart cart = cartRepository.findByMemberId(member.getId());
        if (cart == null) {
            cart = Cart.createCart(member);
            cartRepository.save(cart);
        }

        CartProduct savedCartProduct =
                cartProductRepository.findByCart_IdAndProductId(cart.getId(),
                        product.getId());

        if (savedCartProduct != null) {
            savedCartProduct.addCount(cartProductDto.getCount());
            return savedCartProduct.getId();
        } else {
            CartProduct cartProduct = CartProduct.createCartProduct(cart,
                    product, cartProductDto.getCount());
            cartProductRepository.save(cartProduct);
            return cartProduct.getId();
        }
    }

    @Transactional(readOnly = true)
    public List<CartDetailDto> getCartList(String email){

        List<CartDetailDto> cartDetailDtoList = new ArrayList<>();

        Member member = memberRepository.findByEmail(email);
        Cart cart = cartRepository.findByMemberId(member.getId());
        if(cart == null){
            return cartDetailDtoList;
        }

        cartDetailDtoList = cartProductRepository.findCartDetailDtoList(cart.getId());
        return cartDetailDtoList;
    }

    @Transactional(readOnly = true)
    public boolean validateCartProduct(Long cartProductId, String email) {
        Member curMember = memberRepository.findByEmail(email);
        CartProduct cartProduct = cartProductRepository.findById(cartProductId)
                .orElseThrow(EntityNotFoundException::new);
        Member savedMember = cartProduct.getCart().getMember();

        if (!StringUtils.equals(curMember.getEmail(), savedMember.getEmail())){
            return false;
        }
        return true;
    }

    public void updateCartProductCount(Long cartProductId, int count) {
        CartProduct cartProduct = cartProductRepository.findById(cartProductId)
                .orElseThrow(EntityNotFoundException::new);

        cartProduct.updateCount(count);
    }

}
