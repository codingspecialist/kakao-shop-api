package com.example.kakao.products;

import lombok.*;

import static org.springframework.beans.BeanUtils.copyProperties;

@Data
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductDto {

    private int productId;
    private String productName;
    private String description;
    private String image;
    private int price;

    public ProductDto(Product source) {
        copyProperties(source, this);
    }
}
