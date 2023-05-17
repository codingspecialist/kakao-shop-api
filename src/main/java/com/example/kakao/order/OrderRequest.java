package com.example.kakao.order;

import com.example.kakao.option.Option;
import com.example.kakao.order.item.Item;
import lombok.*;

public class OrderRequest {

    @Getter @Setter
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
}
