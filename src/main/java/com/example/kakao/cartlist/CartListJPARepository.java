package com.example.kakao.cartlist;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;


public interface CartListJPARepository extends JpaRepository<CartList, Integer> {
    List<CartList> findAllByCartId(int cartId);

    List<CartList> findByCartIdOrderByOptionIdAsc(int cartId);

    @Transactional
    List<CartList> deleteByCartId(int cartId);
}
