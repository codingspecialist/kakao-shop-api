package com.example.kakao.cart;

import com.example.kakao._core.errors.exception.Exception400;
import com.example.kakao._core.errors.exception.Exception404;
import com.example.kakao.option.Option;
import com.example.kakao.option.OptionJPARepository;
import com.example.kakao.order.Order;
import com.example.kakao.order.OrderRequest;
import com.example.kakao.order.OrderResponse;
import com.example.kakao.order.item.Item;
import com.example.kakao.product.Product;
import com.example.kakao.product.ProductJPARepository;
import com.example.kakao.product.ProductResponse;
import com.example.kakao.user.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class CartService {

    private final CartJPARepository cartJPARepository;
    private final OptionJPARepository optionJPARepository;
    private final ProductJPARepository productJPARepository;

    @Transactional
    public void addCartList(List<CartRequest.SaveDTO> requestDTOs, User user) {
        // 동일한 옵션이 들어오면 예외처리
        Set<Integer> optionIds = new HashSet<>();
        for (CartRequest.SaveDTO cart : requestDTOs) {
            if (!optionIds.add(cart.getOptionId())) {
                throw new Exception400("동일한 옵션이 중복되어 들어왔습니다: " + cart.getOptionId());
            }
        }

        List<Cart> cartList = requestDTOs.stream().map(cartDTO -> {
            Option optionPS = optionJPARepository.findById(cartDTO.getOptionId()).orElseThrow(
                    ()-> new Exception404("해당 옵션을 찾을 수 없습니다 : "+cartDTO.getOptionId())
            );
            return cartDTO.toEntity(optionPS, user);
        }).collect(Collectors.toList());

        cartJPARepository.saveAll(cartList);
    }

    @Transactional
    public void update(List<CartRequest.UpdateDTO> requestDTOs, User user) {
        // 동일한 옵션이 들어오면 예외처리
        Set<Integer> optionIds = new HashSet<>();
        for (CartRequest.UpdateDTO cart : requestDTOs) {
            if (!optionIds.add(cart.getOptionId())) {
                throw new Exception400("동일한 옵션이 중복되어 들어왔습니다: " + cart.getOptionId());
            }
        }

        List<Cart> cartList = cartJPARepository.findAllByUserId(user.getId());

        requestDTOs.stream().forEach(updateDTO -> {
            cartList.stream().forEach(cart -> {
                if(cart.getId() == updateDTO.getId()){
                    cart.update(updateDTO.getQuantity(), cart.getPrice() * updateDTO.getQuantity());
                }
            });
        });
    } // 더티체킹

    public CartResponse.FindAllDTO findAll(User user) {
        List<Cart> cartLists = cartJPARepository.findByUserIdOrderByOptionIdAsc(user.getId());
        return new CartResponse.FindAllDTO(cartLists);
    }




    public void clear(User user) {
        cartJPARepository.deleteByUserId(user.getId());
    }
}
