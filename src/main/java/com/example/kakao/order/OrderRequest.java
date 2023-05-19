package com.example.kakao.order;

import com.example.kakao.option.Option;
import com.example.kakao.order.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

public class OrderRequest {

    @Getter @Setter
    public static class SaveItemDTO {
        @NotNull
        private int optionId;
        @NotNull
        private int quantity;

        public Item toEntity(Option option, Order order) {
            return Item.builder()
                    .option(option)
                    .order(order)
                    .quantity(quantity)
                    .price(option.getPrice() * quantity)
                    .build();
        }
    }
}
