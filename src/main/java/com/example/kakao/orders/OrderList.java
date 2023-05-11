package com.example.kakao.orders;

import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name="order_list_tb")
public class OrderList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int optionId;
    private int quantity;
    private int price;
    private int orderId;

    @Builder
    public OrderList(int id, int optionId, int quantity, int price, int orderId) {
        this.id = id;
        this.optionId = optionId;
        this.quantity = quantity;
        this.price = price;
        this.orderId = orderId;
    }
}
