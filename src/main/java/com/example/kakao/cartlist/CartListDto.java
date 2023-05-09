package com.example.kakao.cartlist;

import lombok.*;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CartListDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Builder
    public static class Request {
        private int cartListId;
        private int cartId;
        private int optionId;
        private int quantity;
        private int price;

        public CartList toEntity() {
            CartList cartList = CartList.builder()
                    .cartListId(cartListId)
                    .cartId(cartId)
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
        private int cartListId;

        private int cartId;
        private int optionId;
        private int quantity;
        private int price;
        private String productName;
        private String optionName;

        public Response(CartList cartList) {
            this.cartListId = cartList.getCartListId();
            this.cartId = cartList.getCartId();
            this.optionId = cartList.getOptionId();
            this.quantity = cartList.getQuantity();
            this.price = cartList.getPrice();
        }


        public Response(Response response) {
            this.cartListId = response.getCartListId();
            this.cartId = response.getCartId();
            this.optionId = response.getOptionId();
            this.quantity = response.getQuantity();
            this.price = response.getPrice();
            this.productName = response.getProductName();
            this.optionName = response.getOptionName();
        }
    }
}