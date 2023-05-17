package com.example.kakao.option;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class OptionService {

    private final OptionJPARepository optionRepository;

    public List<OptionResponse.FindByProductIdDTO> findByProductId(int id) {
        List<Option> optionList = optionRepository.findByProductId(id);
        List<OptionResponse.FindByProductIdDTO> responseDTO = optionList.stream().map(OptionResponse.FindByProductIdDTO::new).collect(Collectors.toList());
        return responseDTO;
    }

    public List<OptionResponse.FindAllDTO> findAll() {
        List<Option> optionList = optionRepository.findAll();
        List<OptionResponse.FindAllDTO> responseDTO = optionList.stream().map(OptionResponse.FindAllDTO::new).collect(Collectors.toList());
        return responseDTO;
    }

    public String findOptionNameByOptionId(int id) {
        return optionRepository.findById(id).get().getOptionName();
    }
}
