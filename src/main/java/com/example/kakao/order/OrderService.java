package com.example.kakao.order;

import com.example.kakao._core.errors.exception.Exception400;
import com.example.kakao._core.errors.exception.Exception404;
import com.example.kakao._core.errors.exception.Exception500;
import com.example.kakao.cart.Cart;
import com.example.kakao.cart.CartJPARepository;
import com.example.kakao.option.Option;
import com.example.kakao.option.OptionJPARepository;
import com.example.kakao.order.item.Item;
import com.example.kakao.order.item.ItemJPARepository;
import com.example.kakao.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class OrderService {

    private final ItemJPARepository ItemJPARepository;
    private final OrderJPARepository orderJPARepository;
    private final OptionJPARepository optionJPARepository;
    private final CartJPARepository cartJPARepository;

    @Transactional
    public OrderResponse.FindByIdDTO saveOrder(User user) {
        // 1. 유저 장바구니 조회
        List<Cart> cartListPS = cartJPARepository.findAllByUserId(user.getId());
        if(cartListPS.size() == 0){
            throw new Exception404("장바구니에 아무 내역도 존재하지 않습니다");
        }

        // 2. 주문 생성
        Order orderPS = orderJPARepository.save(Order.builder().user(user).build());

        // 3. 주문 아이템 저장
        List<Item> itemList = new ArrayList<>();
        for (Cart cartPS : cartListPS) {
            Item item = Item.builder()
                    .option(cartPS.getOption())
                    .order(orderPS)
                    .quantity(cartPS.getQuantity())
                    .price(cartPS.getOption().getPrice() * cartPS.getQuantity())
                    .build();
            itemList.add(item);
        }
        try {
            ItemJPARepository.saveAll(itemList);
        }catch (Exception e){
            throw new Exception500("결재 실패 : "+e.getMessage());
        }

        // 4. 유저 장바구니 초기화 (결재가 끝나면 장바구니가 초기화 됨)
        try {
            cartJPARepository.deleteByUserId(user.getId());
        }catch (Exception e){
            throw new Exception500("장바구니 초기화 실패 : "+e.getMessage());
        }

        return new OrderResponse.FindByIdDTO(orderPS, itemList);
    }

    public OrderResponse.FindByIdDTO findById(int id) {
        Order orderPS = orderJPARepository.findById(id).orElseThrow(
                ()-> new Exception404("해당 주문을 찾을 수 없습니다 : "+id)
        );
        List<Item> itemList = ItemJPARepository.findAllByOrderId(id);
        return new OrderResponse.FindByIdDTO(orderPS, itemList);
    }

    public void clear() {
        try {
            ItemJPARepository.deleteAll();
        }catch (Exception e){
            throw new Exception500("아이템 삭제 오류 : "+e.getMessage());
        }

    }

//    @Transactional
//    public OrderResponse.SaveDTO saveOrderV1(List<OrderRequest.SaveItemDTO> requestDTOs, User user) {
//        // 1. 동일한 옵션이 들어오면 예외처리
//        Set<Integer> optionIds = new HashSet<>();
//        for (OrderRequest.SaveItemDTO item : requestDTOs) {
//            if (!optionIds.add(item.getOptionId())) {
//                throw new Exception400("동일한 옵션이 중복되어 들어왔습니다: " + item.getOptionId());
//            }
//        }
//
//        // 2. 주문 생성
//        Order order = new Order();
//        order.setUser(user);
//        Order orderPS = orderJPARepository.save(order);
//
//
//        // 3. 아이템 리스트 DB 저장
//        List<Item> itemList = new ArrayList<>();
//        for(OrderRequest.SaveItemDTO requestDTO : requestDTOs) {
//            Option optionPS = optionJPARepository.findById(requestDTO.getOptionId()).get();
//            Item item = requestDTO.toEntity(optionPS, orderPS);
//            itemList.add(item);
//        }
//        try {
//            ItemJPARepository.saveAll(itemList);
//        }catch (Exception e){
//            throw new Exception500("결재 실패 : "+e.getMessage());
//        }
//
//        // 4. 장바구니 초기화 (결재가 끝나면 장바구니가 초기화 됨)
//        try {
//            cartJPARepository.deleteByUserId(user.getId());
//        }catch (Exception e){
//            throw new Exception500("장바구니 초기화 실패 : "+e.getMessage());
//        }
//
//
//        return new OrderResponse.SaveDTO(order.getId());
//    }
}
