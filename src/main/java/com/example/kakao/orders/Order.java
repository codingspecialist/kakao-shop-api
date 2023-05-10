package com.example.kakao.orders;

import lombok.*;

import javax.persistence.*;

@Setter // 삭제 예정
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name="order_tb")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;
    private int userId;

    @Builder
    public Order(int orderId, int userId) {
        this.orderId = orderId;
        this.userId = userId;
    }
}
