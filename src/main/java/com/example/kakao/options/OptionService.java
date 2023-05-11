package com.example.kakao.options;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OptionService {

    private final OptionJPARepository optionRepository;

    public List<Option> findByProductId(int productId) {
        return optionRepository.findByProductId(productId);
    }

    public List<Option> findAll() {
        return optionRepository.findAll();
    }

    public String findOptionNameByOptionId(int id) {
        return optionRepository.findById(id).get().getOptionName();
    }
}
