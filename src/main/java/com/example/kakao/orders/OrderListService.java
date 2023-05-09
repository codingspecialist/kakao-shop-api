package com.example.kakao.orders;

import com.example.kakao.options.Option;
import com.example.kakao.options.OptionJPARepository;
import com.example.kakao.products.Product;
import com.example.kakao.products.ProductJPARepository;
import com.example.kakao.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderListService {

    @Autowired
    OrderListJPARepository orderListJPARepository;

    @Autowired
    OrderJPARepository orderJPARepository;

    @Autowired
    OptionJPARepository optionJPARepository;
    @Autowired
    ProductJPARepository productJPARepository;

    public List<OrderList> saveOrderList(List<OrderListDto.Request> orderListDTOs, User user) {
        Order order = new Order();
        order.setMemberId(user.getMemberId());

        Order newOrder = orderJPARepository.save(order);

        List<OrderList> newOrderLists = new ArrayList<>();

        for(OrderListDto.Request orderListDTO : orderListDTOs) {
            orderListDTO.setOrderId(newOrder.getOrderId());
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
            Option option = optionJPARepository.findByOptionId(orderList.getOptionId()).get();
            Product product = productJPARepository.findByProductId(option.getProductId()).get();
            response.setProductName(product.getProductName());
            response.setOptionName(option.getOptionName());

            orderListDTOs.add(response);
        }

        return orderListDTOs;
    }

    /**
     * order 테이블 비우기
     */
    public void clear() {
        orderListJPARepository.deleteAll();
    }
}
