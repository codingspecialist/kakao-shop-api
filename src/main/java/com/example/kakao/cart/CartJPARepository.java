package com.example.kakao.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface CartJPARepository extends JpaRepository<Cart, Integer> {
    List<Cart> findAllByUserId(int userId);
    List<Cart> findByUserIdOrderByOptionIdAsc(int userId);
    List<Cart> deleteByUserId(int userId);
}
