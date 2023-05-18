package com.example.kakao.order;

import com.example.kakao.user.User;
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

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Builder
    public Order(int id, User user) {
        this.id = id;
        this.user = user;
    }
}
