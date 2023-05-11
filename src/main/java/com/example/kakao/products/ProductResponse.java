package com.example.kakao.products;

import com.example.kakao.delivery.DeliveryFee;
import com.example.kakao.options.Option;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductResponse {

    @Getter @Setter
    public static class FindAllDTO {

        List<ProductDTO> products;

        public FindAllDTO(List<Product> products) {
            this.products = products.stream().map(ProductDTO::new).collect(Collectors.toList());
        }

        @Getter @Setter
        public class ProductDTO {
            private int id;
            private String productName;
            private String description;
            private String image;
            private int price;

            public ProductDTO(Product product) {
                this.id = product.getId();
                this.productName = product.getProductName();
                this.description = product.getDescription();
                this.image = product.getImage();
                this.price = product.getPrice();
            }
        }
    }


    @Getter @Setter
    public static class FindByIdDTO {

        private int id;
        private String productName;
        private String description;
        private String image;
        private int price;
        private List<OptionDTO> options;
        private List<DeliveryFeeDTO> deliveryFees;

        public FindByIdDTO(Product product, List<Option> optionList, List<DeliveryFee> deliveryFeeList) {
            this.id = product.getId();
            this.productName = product.getProductName();
            this.description = product.getDescription();
            this.image = product.getImage();
            this.price = product.getPrice();
            this.options = optionList.stream().map(OptionDTO::new).collect(Collectors.toList());
            this.deliveryFees = deliveryFeeList.stream().map(DeliveryFeeDTO::new).collect(Collectors.toList());
        }

        @Getter @Setter
        public class OptionDTO {
            private int id;
            private String optionName;
            private int price;

            public OptionDTO(Option option) {
                this.id = option.getId();
                this.optionName = option.getOptionName();
                this.price = option.getPrice();
            }
        }

        @Getter @Setter
        public class DeliveryFeeDTO {
            private int id;
            private String region;
            private int fee;

            public DeliveryFeeDTO(DeliveryFee deliveryFee) {
                this.id = deliveryFee.getId();
                this.region = deliveryFee.getRegion();
                this.fee = deliveryFee.getFee();
            }
        }
    }
}
