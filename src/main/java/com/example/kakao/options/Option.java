package com.example.kakao.options;

import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name="option_tb")
public class Option {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int optionId;
    private int productId;
    private String optionName;
    private int price;

    @Builder
    public Option(int optionId, int productId, String optionName, int price) {
        this.optionId = optionId;
        this.productId = productId;
        this.optionName = optionName;
        this.price = price;
    }
}
