package com.example.kakao.options;

import com.example.kakao.utils.ApiUtils.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.example.kakao.utils.ApiUtils.success;
import static java.util.stream.Collectors.toList;

@RestController
public class OptionRestController {

    @Autowired
    private OptionService optionService;

    /**
     * @param id
     * ProductId에 해당하는 Option 조회
     * @return
     * 성공 시 Option 리스트 반환
     */
    @GetMapping("/products/{id}/option")
    public ApiResult<List<OptionDto>> findByProductId(@PathVariable int id) {
        return success(optionService.findByProductId(id).stream()
                .map(OptionDto::new)
                .collect(toList()));
    }

    /**
     * @return
     * Option 전체 반환
     */
    @GetMapping("/options")
    public ApiResult<List<OptionDto>> findAll() {
        return success(optionService.findAll().stream()
                .map(OptionDto::new)
                .collect(toList()));
    }
}
