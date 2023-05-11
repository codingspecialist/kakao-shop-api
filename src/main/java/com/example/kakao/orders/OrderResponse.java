package com.example.kakao.orders;

import com.example.kakao.options.Option;
import com.example.kakao.orders.item.Item;
import com.example.kakao.products.Product;
import com.example.kakao.users.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

public class OrderResponse {

    @Getter @Setter
    public static class SaveDTO {
        private int id;
        private UserDTO user;
        private List<ProductDTO> products;
        private int totalPrice;

        public SaveDTO(Order order, List<Item> itemList) {
            this.id = order.getId();
            this.user = new UserDTO(order.getUser());
            this.products = itemList.stream()
                    .map(item -> item.getOption().getProduct()).distinct()
                    .map(product -> new ProductDTO(itemList, product)).collect(Collectors.toList());
            this.totalPrice = itemList.stream().mapToInt(item -> item.getOption().getPrice() * item.getQuantity()).sum();
        }

        @Getter @Setter
        public class UserDTO {
            private int id;

            public UserDTO(User user) {
                this.id = user.getId();
            }
        }

        @Getter @Setter
        public class ProductDTO {
            private int id;
            private String productName;
            private String description;
            private String image;
            private List<ItemDTO> items;

            public ProductDTO(List<Item> itemList, Product product) {
                this.id = product.getId();
                this.productName = product.getProductName();
                this.description = product.getDescription();
                this.image = product.getImage();
                this.items = itemList.stream()
                        .filter(item -> item.getOption().getProduct().getId() == product.getId())
                        .map(ItemDTO::new)
                        .collect(Collectors.toList());
            }

            @Getter @Setter
            public class ItemDTO {
                private int id;
                private OptionDTO option;
                private int quantity;
                private int price;

                public ItemDTO(Item item) {
                    this.id = item.getId();
                    this.option = new OptionDTO(item.getOption());
                    this.quantity = item.getQuantity();
                    this.price = item.getOption().getPrice()*item.getQuantity();
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
            }
        }
    }
}
