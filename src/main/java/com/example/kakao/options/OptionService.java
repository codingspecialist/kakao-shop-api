package com.example.kakao.options;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class OptionService {

    private final OptionJPARepository optionRepository;

    public OptionResponse.FindByProductIdDTO findByProductId(int id) {
        List<Option> optionList = optionRepository.findByProductId(id);
        return new OptionResponse.FindByProductIdDTO(optionList);
    }

    public OptionResponse.FindAllDTO findAll() {
        List<Option> optionList = optionRepository.findAll();
        return new OptionResponse.FindAllDTO(optionList);
    }

    public String findOptionNameByOptionId(int id) {
        return optionRepository.findById(id).get().getOptionName();
    }
}
