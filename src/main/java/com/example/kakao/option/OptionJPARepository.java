package com.example.kakao.option;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface OptionJPARepository extends JpaRepository<Option, Integer> {

    List<Option> findByProductId(int productId);
    Optional<Option> findById(int id);
}
