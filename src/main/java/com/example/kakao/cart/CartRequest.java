package com.example.kakao.cart;

import lombok.Getter;
import lombok.Setter;

public class CartRequest {

    @Getter @Setter
    public static class SaveDTO {
        private int userId;
        private int optionId;
        private int quantity;

        public Cart toEntity(int price) {
            Cart cart = Cart.builder()
                    .userId(userId)
                    .optionId(optionId)
                    .quantity(quantity)
                    .price(price)
                    .build();
            return cart;
        }
    }

    @Getter @Setter
    public static class UpdateDTO {
        private int id;
        private int userId;
        private int optionId;
        private int quantity;

        public Cart toEntity(int price) {
            Cart cart = Cart.builder()
                    .id(id)
                    .userId(userId)
                    .optionId(optionId)
                    .quantity(quantity)
                    .price(price)
                    .build();
            return cart;
        }
    }
}
