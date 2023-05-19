package com.example.kakao.cart;

import com.example.kakao._core.errors.exception.Exception400;
import com.example.kakao._core.errors.exception.Exception404;
import com.example.kakao._core.errors.exception.Exception500;
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
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class CartService {

    private final CartJPARepository cartJPARepository;
    private final OptionJPARepository optionJPARepository;

    /**
     * 1. 모든 제약 조건을 주고, try catch로 제어하는 법 (transaction 시간 길어짐)
     * 2. 미리 영속화 시켜서 write 하기전에 값을 검증하는 법 (select 많아짐)
     *
     * 무결성을 체크하기 위해 여러 번의 select 작업을 수행하는 방법은 비효율적이며,
     * 동시에 여러 사용자가 동시에 작업할 때 데이터 일관성을 보장하기 어려울 수 있습니다.
     * 따라서, 데이터베이스 제약 조건을 사용하여 데이터의 무결성을 보장하고,
     * insert 작업 시에 오류를 제어하는 것이 일반적으로 성능과 데이터 일관성 면에서 더 우수한 선택입니다.
     */

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

        cartList.forEach(cart -> {
            try {
                cartJPARepository.save(cart);
            }catch (Exception e){
                System.out.println(e.getMessage());
                throw new Exception500("해당 옵션은 이미 장바구니에 담겨 있습니다 : "+cart.getOption().getId());
            }
        });
    }

    @Transactional
    public void update(List<CartRequest.UpdateDTO> requestDTOs, User user) {
        List<Cart> cartList = cartJPARepository.findAllByUserId(user.getId());

        List<Integer> cartIds = cartList.stream().map(cart -> cart.getId()).collect(Collectors.toList());
        List<Integer> requestIds =requestDTOs.stream().map(dto -> dto.getId()).collect(Collectors.toList());

        if(cartIds.size() == 0){
            throw new Exception404("주문할 장바구니 상품이 없습니다");
        }

        if(requestIds.stream().distinct().count() != requestIds.size()){
            throw new Exception400("동일한 장바구니 아이디를 주문할 수 없습니다");
        }

        if(cartIds.size() != requestIds.size()){
            throw new Exception400("주문시에는 수량만 업데이트할 수 있습니다");
        }

        for (Integer requestId : requestIds) {
            if(!cartIds.contains(requestId)){
                throw new Exception400("장바구니에 없는 상품은 주문할 수 없습니다 : "+requestId);
            }
        }


        // 장바구니 수량이 동일하다면 업데이트는 되지 않는다.
        for (CartRequest.UpdateDTO updateDTO : requestDTOs) {
            for (Cart cart : cartList) {
                cart.update(updateDTO.getQuantity(), cart.getPrice() * updateDTO.getQuantity());
            }
        }
    } // 더티체킹

    public CartResponse.FindAllDTO findAll(User user) {
        List<Cart> cartLists = cartJPARepository.findByUserIdOrderByOptionIdAsc(user.getId());
        return new CartResponse.FindAllDTO(cartLists);
    }

    @Transactional
    public void clear(User user) {
        cartJPARepository.deleteByUserId(user.getId());
    }



    @Transactional
    public void addCartListV1(List<CartRequest.SaveDTO> requestDTOs, User user) {
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
            Optional<Cart> cartOP = cartJPARepository.findByUserIdAndOptionId(user.getId(), optionPS.getId());
            if(cartOP.isPresent()){
                throw new Exception500("해당 옵션은 이미 장바구니에 담겨 있습니다 : "+optionPS.getId());
            }

            return cartDTO.toEntity(optionPS, user);
        }).collect(Collectors.toList());

        try {
            cartJPARepository.saveAll(cartList);
        }catch (Exception e){
            throw new Exception500(e.getMessage());
        }
    }

    @Transactional
    public void addCartListV2(List<CartRequest.SaveDTO> requestDTOs, User user) {
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

        cartList.stream().forEach(cart -> {
            try {
                cartJPARepository.save(cart);
            }catch (DataIntegrityViolationException e){
                e.printStackTrace();
                throw new Exception500("해당 옵션은 이미 장바구니에 담겨 있습니다 : "+cart.getOption().getId());
            }
        });
    }
}
