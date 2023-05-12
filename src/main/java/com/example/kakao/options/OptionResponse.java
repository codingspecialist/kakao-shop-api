package com.example.kakao.options;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.beans.BeanUtils.copyProperties;

public class OptionResponse {

    @Getter @Setter
    public static class FindAllDTO {

        private List<OptionDTO> options;

        public FindAllDTO(List<Option> options) {
            this.options = options.stream().map(OptionDTO::new).collect(Collectors.toList());
        }

        @Getter @Setter
        public class OptionDTO {
            private int id;
            private int productId;
            private String optionName;
            private int price;

            public OptionDTO(Option option) {
                this.id = option.getId();
                this.productId = option.getProduct().getId();
                this.optionName = option.getOptionName();
                this.price = option.getPrice();
            }
        }
    }

    @Getter @Setter
    public static class FindByProductIdDTO {

        private List<OptionDTO> options;

        public FindByProductIdDTO(List<Option> options) {
            this.options = options.stream().map(OptionDTO::new).collect(Collectors.toList());
        }

        @Getter @Setter
        public class OptionDTO {
            private int id;
            private int productId;
            private String optionName;
            private int price;

            public OptionDTO(Option option) {
                this.id = option.getId();
                this.productId = option.getProduct().getId();
                this.optionName = option.getOptionName();
                this.price = option.getPrice();
            }
        }
    }
}
