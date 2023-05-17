package com.example.kakao.cart;

import lombok.*;

import javax.persistence.*;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name="cart_tb")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int userId;
    private int optionId;
    private int quantity;
    private int price;

    @Builder
    public Cart(int id, int userId, int optionId, int quantity, int price) {
        this.id = id;
        this.userId = userId;
        this.optionId = optionId;
        this.quantity = quantity;
        this.price = price;
    }
}