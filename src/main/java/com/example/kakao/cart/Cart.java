package com.example.kakao.cart;

import com.example.kakao.option.Option;
import com.example.kakao.user.User;
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

    @ManyToOne(fetch = FetchType.LAZY)
    private User user; // user별로 장바구니에 묶여 있음.

    @OneToOne(fetch = FetchType.LAZY)
    private Option option;
    private int quantity;
    private int price;

    @Builder
    public Cart(int id, User user, Option option, int quantity, int price) {
        this.id = id;
        this.user = user;
        this.option = option;
        this.quantity = quantity;
        this.price = price;
    }

    public void update(int quantity, int price){
        this.quantity = quantity;
        this.price = price;
    }
}