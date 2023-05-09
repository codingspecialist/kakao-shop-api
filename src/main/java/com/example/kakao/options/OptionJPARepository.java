package com.example.kakao.options;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OptionJPARepository extends JpaRepository<Option, Integer> {

    List<Option> findByProductId(int productId);

    Optional<Option> findByOptionId(int optionId);
}
