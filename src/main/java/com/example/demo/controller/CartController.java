package com.example.demo.controller;

import com.example.demo.dto.CartDetailDto;
import com.example.demo.dto.CartProductDto;
import com.example.demo.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping(value = "/cart")
    public @ResponseBody
    ResponseEntity order(@RequestBody @Valid CartProductDto cartProductDto,
                                              BindingResult bindingResult, Principal principal) {

        if (bindingResult.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                sb.append(fieldError.getDefaultMessage());
            }

            return new ResponseEntity<String>(sb.toString(),
                    HttpStatus.BAD_REQUEST);
        }

        String email = principal.getName();
        Long cartProductId;

        try {
            cartProductId = cartService.addCart(cartProductDto, email);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<Long>(cartProductId, HttpStatus.OK);
    }

    // 목록 조회
    @GetMapping(value = "/cart")
    public String orderHist(Principal principal, Model model){
        List<CartDetailDto> cartDetailList =
                cartService.getCartList(principal.getName());
        model.addAttribute("cartProducts", cartDetailList);
        return "cart/cartList";
    }

    // 수량 변경
    @PostMapping(value = "/cartProduct/{cartProductId}")
    public @ResponseBody ResponseEntity updateCartProduct(
            @PathVariable("cartProducrId") Long cartProductId, int count,
                                                          Principal principal){
        if (count<=0) {
            return new ResponseEntity<String>("최소 1개 이상 담아주세요",
                    HttpStatus.BAD_REQUEST);
        } else if (!cartService.validateCartProduct(cartProductId, principal.getName()
                )) {
            return new ResponseEntity<String>("수정 권한이 없습니다",
                    HttpStatus.FORBIDDEN);
        }

        cartService.updateCartProductCount(cartProductId, count);
        return new ResponseEntity<Long>(cartProductId, HttpStatus.OK);
    }

    // 상품 삭제
    @DeleteMapping(value = "/cartProduct/{cartProductId}")
    public @ResponseBody ResponseEntity deleteCartProduct(
            @PathVariable("cartProductId") Long cartProductId,
            Principal principal) {
        if (!cartService.validateCartProduct(cartProductId, principal.getName())){
            return new ResponseEntity<String>("수정 권한이 없습니다.",
                    HttpStatus.FORBIDDEN);
        }

        cartService.deleteCartProduct(cartProductId);
        return new ResponseEntity<Long>(cartProductId, HttpStatus.OK);
    }
}
