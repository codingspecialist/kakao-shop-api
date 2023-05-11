package com.example.kakao.orders;

import com.example.kakao.options.Option;
import com.example.kakao.options.OptionJPARepository;
import com.example.kakao.products.Product;
import com.example.kakao.products.ProductJPARepository;
import com.example.kakao.users.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderListService {

    private final OrderListJPARepository orderListJPARepository;
    private final OrderJPARepository orderJPARepository;
    private final OptionJPARepository optionJPARepository;
    private final ProductJPARepository productJPARepository;

    public List<OrderList> saveOrderList(List<OrderListDto.Request> orderListDTOs, User user) {
        Order order = new Order();
        order.setUserId(user.getId());

        Order newOrder = orderJPARepository.save(order);

        List<OrderList> newOrderLists = new ArrayList<>();

        for(OrderListDto.Request orderListDTO : orderListDTOs) {
            orderListDTO.setOrderId(newOrder.getId());
            OrderList newOrderList = orderListDTO.toEntity();
            newOrderLists.add(newOrderList);
        }

        return orderListJPARepository.saveAll(newOrderLists);
    }

    public List<OrderListDto.Response> findAll(int orderId) {
        List<OrderList> orderLists = orderListJPARepository.findAllByOrderId(orderId);//findAll();
        List<OrderListDto.Response> orderListDTOs = new ArrayList<>();

        for(OrderList orderList : orderLists) {
            OrderListDto.Response response = new OrderListDto.Response(orderList);
            Option option = optionJPARepository.findById(orderList.getOptionId()).get();
            Product product = productJPARepository.findById(option.getProduct().getId()).get();
            response.setProductName(product.getProductName());
            response.setOptionName(option.getOptionName());

            orderListDTOs.add(response);
        }

        return orderListDTOs;
    }

    public void clear() {
        orderListJPARepository.deleteAll();
    }
}
