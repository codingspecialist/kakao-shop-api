package com.example.kakao.order;

import com.example.kakao._core.errors.exception.Exception400;
import com.example.kakao._core.errors.exception.Exception404;
import com.example.kakao.option.Option;
import com.example.kakao.option.OptionJPARepository;
import com.example.kakao.order.item.Item;
import com.example.kakao.order.item.ItemJPARepository;
import com.example.kakao.product.ProductJPARepository;
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
    private final ProductJPARepository productJPARepository;

    @Transactional
    public OrderResponse.SaveDTO saveOrder(List<OrderRequest.SaveItemDTO> requestDTOs, User user) {
        // 주문 생성
        Order order = new Order();
        order.setUser(user);
        Order orderPS = orderJPARepository.save(order);

        // 동일한 옵션이 들어오면 예외처리
        Set<Integer> optionIds = new HashSet<>();
        for (OrderRequest.SaveItemDTO item : requestDTOs) {
            if (!optionIds.add(item.getOptionId())) {
                throw new Exception400("동일한 옵션이 중복되어 들어왔습니다: " + item.getOptionId());
            }
        }

        // 아이템 리스트 DB 저장
        List<Item> itemList = new ArrayList<>();
        for(OrderRequest.SaveItemDTO requestDTO : requestDTOs) {
            Option optionPS = optionJPARepository.findById(requestDTO.getOptionId()).get();
            Item item = requestDTO.toEntity(optionPS, orderPS);
            itemList.add(item);
        }

        List<Item> itemListPS = ItemJPARepository.saveAll(itemList);

        return new OrderResponse.SaveDTO(order,itemListPS);
    }

    public OrderResponse.FindALLDTO findAll(int id) {
        Order orderPS = orderJPARepository.findById(id).orElseThrow(
                ()-> new Exception404("해당 주문을 찾을 수 없습니다 : "+id)
        );
        List<Item> itemList = ItemJPARepository.findAllByOrderId(id);//findAll();

        return new OrderResponse.FindALLDTO(orderPS, itemList);
    }

    public void clear() {
        ItemJPARepository.deleteAll();
    }
}
