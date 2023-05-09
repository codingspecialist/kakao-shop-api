package com.example.kakao.options;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="option_tb")
public class Option {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int optionId;
    private int productId;
    private String optionName;
    private int price;

    @AllArgsConstructor
    public static class Builder {
        private int optionId;
        private int productId;
        private String optionName;
        private int price;

        public Option build() {
            return new Option(optionId, productId, optionName, price);
        }
    }
}
