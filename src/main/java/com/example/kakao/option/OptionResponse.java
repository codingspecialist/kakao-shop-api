package com.example.kakao.option;

import lombok.Getter;
import lombok.Setter;

public class OptionResponse {

    @Getter @Setter
    public static class FindAllDTO {

        private int id;
        private int productId;
        private String optionName;
        private int price;

        public FindAllDTO(Option option) {
            this.id = option.getId();
            this.productId = option.getProduct().getId();
            this.optionName = option.getOptionName();
            this.price = option.getPrice();
        }
    }

    @Getter @Setter
    public static class FindByProductIdDTO {

        private int id;
        private int productId;
        private String optionName;
        private int price;

        public FindByProductIdDTO(Option option) {
            this.id = option.getId();
            this.productId = option.getProduct().getId();
            this.optionName = option.getOptionName();
            this.price = option.getPrice();
        }
    }
}
