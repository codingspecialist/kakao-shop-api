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
    private int id;
    private int userId;

    @Builder
    public Order(int id, int userId) {
        this.id = id;
        this.userId = userId;
    }
}
