package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Getter @Setter
@Table(name="cart_product")
public class CartProduct extends BaseEntity{


    @Id
    @GeneratedValue
    @Column(name = "cart_product_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="cart_id")
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    private int count;

    public static CartProduct createCartProduct(Cart cart, Product product,
                                                int count) {
        CartProduct cartProduct = new CartProduct();
        cartProduct.setCart(cart);
        cartProduct.setProduct(product);
        cartProduct.setCount(count);
        return cartProduct;
    }

    public void addCount(int count) {
        this.count += count;
    }

    //장바구니 수량 변경
    public void updateCount(int count) {
        this.count = count;
    }

}