package com.example.kakao.cart;

import com.example.kakao._core.errors.exception.Exception400;
import com.example.kakao._core.errors.exception.Exception404;
import com.example.kakao.option.Option;
import com.example.kakao.option.OptionJPARepository;
import com.example.kakao.order.OrderRequest;
import com.example.kakao.product.Product;
import com.example.kakao.product.ProductJPARepository;
import com.example.kakao.product.ProductResponse;
import com.example.kakao.user.User;
import lombok.RequiredArgsConstructor;
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
    public List<CartResponse.SaveOrUpdateDTO> addCartList(List<CartRequest.SaveDTO> requestDTOs, User user) {

        List<Cart> cartList = requestDTOs.stream().map(cartDTO -> {
            // 1. 옵션 존재 확인
            Option optionPS = optionJPARepository.findById(cartDTO.getOptionId()).orElseThrow(
                    ()-> new Exception404("해당 옵션을 찾을 수 없습니다 : "+cartDTO.getOptionId())
            );
            // 2. 장바구니에 유저 정보 연결 (한명의 유저는 장바구니를 결재 후 폐기함)
            cartDTO.setUserId(user.getId());
            // 3. 가격은 프론트로 부터 받지 않는다.
            return cartDTO.toEntity(optionPS.getPrice() * cartDTO.getQuantity());
        }).collect(Collectors.toList());

        // 동일한 옵션이 들어오면 예외처리
        Set<Integer> optionIds = new HashSet<>();
        for (CartRequest.SaveDTO item : requestDTOs) {
            if (!optionIds.add(item.getOptionId())) {
                throw new Exception400("동일한 옵션이 중복되어 들어왔습니다: " + item.getOptionId());
            }
        }

        cartJPARepository.saveAll(cartList);

        List<CartResponse.SaveOrUpdateDTO> responseDTO = cartList.stream().map(CartResponse.SaveOrUpdateDTO::new).collect(Collectors.toList());
        return responseDTO;
    }

    @Transactional
    public List<CartResponse.SaveOrUpdateDTO> update(List<CartRequest.UpdateDTO> requestDTOs, User user) {
        List<Cart> cartList = requestDTOs.stream().map(cartDTO -> {
            Option optionPS = optionJPARepository.findById(cartDTO.getOptionId()).orElseThrow(
                    ()-> new Exception404("해당 옵션을 찾을 수 없습니다 : "+cartDTO.getOptionId())
            );
            cartDTO.setUserId(user.getId());
            return cartDTO.toEntity(optionPS.getPrice() * cartDTO.getQuantity());
        }).collect(Collectors.toList());

        // 동일한 옵션이 들어오면 예외처리
        Set<Integer> optionIds = new HashSet<>();
        for (CartRequest.UpdateDTO item : requestDTOs) {
            if (!optionIds.add(item.getOptionId())) {
                throw new Exception400("동일한 옵션이 중복되어 들어왔습니다: " + item.getOptionId());
            }
        }

        cartJPARepository.saveAll(cartList);

        List<CartResponse.SaveOrUpdateDTO> responseDTOs = cartList.stream().map(CartResponse.SaveOrUpdateDTO::new).collect(Collectors.toList());
        return responseDTOs;
    }

    public List<CartListDto.Response> findAll(User user) {
        List<Cart> cartLists = cartJPARepository.findByUserIdOrderByOptionIdAsc(user.getId());
        List<CartListDto.Response> cartListDTOs = new ArrayList<>();

        for(Cart cartList : cartLists) {
            CartListDto.Response response = new CartListDto.Response(cartList);

            Option option = optionJPARepository.findById(cartList.getOptionId()).get();
            Product product = productJPARepository.findById(option.getProduct().getId()).get();
            response.setProductName(product.getProductName());
            response.setOptionName(option.getOptionName());

            cartListDTOs.add(response);
        }

        return cartListDTOs;
    }

    public void clear(User user) {
        cartJPARepository.deleteByUserId(user.getId());
    }
}
