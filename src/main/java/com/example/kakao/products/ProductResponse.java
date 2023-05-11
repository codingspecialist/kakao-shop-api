package com.example.kakao.products;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductResponse {

    @Getter @Setter
    public static class FindAllDTO {

        List<ProductDTO> products = new ArrayList<>();

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

        public FindByIdDTO(Product product) {
            this.id = product.getId();
            this.productName = product.getProductName();
            this.description = product.getDescription();
            this.image = product.getImage();
            this.price = product.getPrice();
        }
    }
}
