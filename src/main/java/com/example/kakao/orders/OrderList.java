package com.example.kakao.orders;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="order_list_tb")
public class OrderList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderListId;
    private int optionId;
    private int quantity;
    private int price;

    private int orderId;

    @Builder
    public OrderList(int orderListId, int optionId, int quantity, int price, int orderId) {
        this.orderListId = orderListId;
        this.optionId = optionId;
        this.quantity = quantity;
        this.price = price;
        this.orderId = orderId;
    }
}
