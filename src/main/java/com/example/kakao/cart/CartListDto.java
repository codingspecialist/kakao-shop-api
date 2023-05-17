package com.example.kakao.cart;

import lombok.*;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CartListDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Builder
    public static class Request {
        private int id;
        private int cartId;
        private int optionId;
        private int quantity;
        private int price;

        public Cart toEntity() {
            Cart cartList = Cart.builder()
                    .id(id)
                    .userId(cartId)
                    .optionId(optionId)
                    .quantity(quantity)
                    .price(price)
                    .build();
            return cartList;
        }
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Response {
        private int id;

        private int userId;
        private int optionId;
        private int quantity;
        private int price;
        private String productName;
        private String optionName;

        public Response(Cart cartList) {
            this.id = cartList.getId();
            this.userId = cartList.getUserId();
            this.optionId = cartList.getOptionId();
            this.quantity = cartList.getQuantity();
            this.price = cartList.getPrice();
        }


        public Response(Response response) {
            this.id = response.getId();
            this.userId = response.getUserId();
            this.optionId = response.getOptionId();
            this.quantity = response.getQuantity();
            this.price = response.getPrice();
            this.productName = response.getProductName();
            this.optionName = response.getOptionName();
        }
    }
}