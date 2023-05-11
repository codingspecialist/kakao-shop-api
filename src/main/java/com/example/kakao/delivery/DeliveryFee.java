package com.example.kakao.delivery;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name="delivery_fee_tb")
public class DeliveryFee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String region; // 전국 0, 제주 3000, 산간 6000
    private int fee; // 0, 3000, 6000

    @Builder
    public DeliveryFee(int id, String region, int fee) {
        this.id = id;
        this.region = region;
        this.fee = fee;
    }
}
