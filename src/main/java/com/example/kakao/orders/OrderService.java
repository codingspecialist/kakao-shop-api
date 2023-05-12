package com.example.kakao.orders;

import com.example.kakao._core.errors.exception.Exception400;
import com.example.kakao.options.Option;
import com.example.kakao.options.OptionJPARepository;
import com.example.kakao.orders.item.Item;
import com.example.kakao.orders.item.ItemRequest;
import com.example.kakao.orders.item.ItemJPARepository;
import com.example.kakao.products.Product;
import com.example.kakao.products.ProductJPARepository;
import com.example.kakao.users.User;
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
    public OrderResponse.SaveDTO saveOrder(List<ItemRequest.SaveItemDTO> requestDTOs, User user) {
        // 주문 생성
        Order order = new Order();
        order.setUser(user);
        Order orderPS = orderJPARepository.save(order);

        // 동일한 옵션이 들어오면 예외처리
        Set<Integer> optionIds = new HashSet<>();
        for (ItemRequest.SaveItemDTO item : requestDTOs) {
            if (!optionIds.add(item.getOptionId())) {
                throw new Exception400("동일한 옵션이 중복되어 들어왔습니다: " + item.getOptionId());
            }
        }

        // 아이템 리스트 DB 저장
        List<Item> itemList = new ArrayList<>();
        for(ItemRequest.SaveItemDTO requestDTO : requestDTOs) {
            Option optionPS = optionJPARepository.findById(requestDTO.getOptionId()).get();
            Item item = requestDTO.toEntity(optionPS, orderPS);
            itemList.add(item);
        }

        List<Item> itemListPS = ItemJPARepository.saveAll(itemList);

        return new OrderResponse.SaveDTO(order,itemListPS);
    }

    public List<ItemRequest.Response> findAll(int id) {
        List<Item> itemList = ItemJPARepository.findAllByOrderId(id);//findAll();
        List<ItemRequest.Response> orderListDTOs = new ArrayList<>();

        for(Item item : itemList) {
            ItemRequest.Response response = new ItemRequest.Response(item);
            Option option = optionJPARepository.findById(item.getOption().getId()).get();
            Product product = productJPARepository.findById(option.getProduct().getId()).get();
            response.setProductName(product.getProductName());
            response.setOptionName(option.getOptionName());

            orderListDTOs.add(response);
        }

        return orderListDTOs;
    }

    public void clear() {
        ItemJPARepository.deleteAll();
    }
}
