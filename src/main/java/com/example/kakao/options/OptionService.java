package com.example.kakao.options;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OptionService {

    @Autowired
    private OptionJPARepository optionRepository;

    public List<Option> findByProductId(int productId) {
        return optionRepository.findByProductId(productId);
    }

    public List<Option> findAll() {
        return optionRepository.findAll();
    }

    public String findOptionNameByOptionId(int id) {
        return optionRepository.findByOptionId(id).get().getOptionName();
    }
}
