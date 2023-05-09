package com.example.kakao.orders;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderListJPARepository extends JpaRepository<OrderList, Integer> {
    List<OrderList> findAllByOrderId(int orderId);
}
