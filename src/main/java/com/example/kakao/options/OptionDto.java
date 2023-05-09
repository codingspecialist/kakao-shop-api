package com.example.kakao.options;

import lombok.*;

import static org.springframework.beans.BeanUtils.copyProperties;

@Getter
@Setter
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OptionDto {
    private int optionId;
    private int productId;
    private String optionName;
    private int price;

    public OptionDto(Option source) {
        copyProperties(source, this);
    }
}
