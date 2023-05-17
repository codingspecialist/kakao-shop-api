package com.example.kakao.cartlist;

import lombok.*;

import javax.persistence.*;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name="cart_list_tb")
public class CartList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int cartId;
    private int optionId;
    private int quantity;
    private int price;

    @Builder
    public CartList(int id, int cartId, int optionId, int quantity, int price) {
        this.id = id;
        this.cartId = cartId;
        this.optionId = optionId;
        this.quantity = quantity;
        this.price = price;
    }
}