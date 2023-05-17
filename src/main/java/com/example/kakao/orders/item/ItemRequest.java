package com.example.kakao.orders.item;

import com.example.kakao.options.Option;
import com.example.kakao.orders.Order;
import lombok.*;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemRequest {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Builder
    public static class SaveItemDTO {
        private int id;
        private int optionId;
        private int orderId;
        private int quantity;
        private int price;

        public Item toEntity(Option option, Order order) {
            return Item.builder()
                    .id(id)
                    .option(option)
                    .order(order)
                    .quantity(quantity)
                    .price(price)
                    .build();
        }
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response {
        private int id;
        private int optionId;
        private int orderId;
        private int quantity;
        private int price;

        private String productName;
        private String optionName;

        public Response(Item item) {
            this.id = item.getId();
            this.optionId = item.getOption().getId();
            this.quantity = item.getQuantity();
            this.price = item.getPrice();
            this.orderId = item.getOrder().getId();
        }


        public Response(ItemRequest.Response response) {
            this.id = response.getId();
            this.optionId = response.getOptionId();
            this.quantity = response.getQuantity();
            this.price = response.getPrice();
            this.orderId = response.getOrderId();
            this.productName = response.getProductName();
            this.optionName = response.getOptionName();
        }
    }
}
