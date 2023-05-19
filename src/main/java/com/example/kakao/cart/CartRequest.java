package com.example.kakao.cart;

import com.example.kakao.option.Option;
import com.example.kakao.user.User;
import lombok.Getter;
import lombok.Setter;

public class CartRequest {

    @Getter @Setter
    public static class SaveDTO {
        private int optionId;
        private int quantity;

        public Cart toEntity(Option option, User user) {
            Cart cart = Cart.builder()
                    .user(user)
                    .option(option)
                    .quantity(quantity)
                    .price(option.getPrice() * quantity)
                    .build();
            return cart;
        }
    }

    @Getter @Setter
    public static class UpdateDTO {
        private int cartId;
        private int quantity;
    }
}
