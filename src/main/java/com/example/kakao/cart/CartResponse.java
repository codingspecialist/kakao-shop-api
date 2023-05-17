package com.example.kakao.cart;

import lombok.Getter;
import lombok.Setter;

public class CartResponse {
    @Getter
    @Setter
    public static class SaveOrUpdateDTO {
        private int id;
        private int userId;
        private int optionId;
        private int quantity;
        private int price;

        public SaveOrUpdateDTO(Cart cart) {
            this.id = cart.getId();
            this.userId = cart.getUserId();
            this.optionId = cart.getOptionId();
            this.quantity = cart.getQuantity();
            this.price = cart.getPrice();
        }
    }
}
