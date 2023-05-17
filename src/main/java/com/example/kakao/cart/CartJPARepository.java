package com.example.kakao.cart;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CartJPARepository extends JpaRepository<Cart, Integer> {
    List<Cart> findAllByUserId(int cartId);

    List<Cart> findByUserIdOrderByOptionIdAsc(int userId);

    List<Cart> deleteByUserId(int cartId);
}
