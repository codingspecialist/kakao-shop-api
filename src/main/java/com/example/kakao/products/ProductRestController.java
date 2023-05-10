package com.example.kakao.products;

import com.example.kakao._core.errors.exception.Exception400;
import com.example.kakao._core.utils.ApiUtils.ApiResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.kakao._core.utils.ApiUtils.success;
import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductRestController {

    private final ProductService productService;

    /**
     * [판매자&구매자 공통 기능] 전체 상품 조회
     * @return
     * 전체 상품 리스트를 반환
     */
    @GetMapping
    public ApiResult<List<ProductDto>> findAll(@RequestParam(defaultValue = "0") int page) {
        return success(productService.findAll(page).stream()
                .map(ProductDto::new)
                .collect(toList()));
    }

    /**
     * [판매자&구매자 공통 기능] 단일 상품 조회
     * @param id // 상품 id
     * 상품 id를 받아서
     * @return
     * 해당 상품 정보를 반환
     */
    @GetMapping("{id}")
    public ApiResult<ProductDto> findById(@PathVariable int id) {
        return success(productService.findById(id)
                .map(ProductDto::new)
                .orElseThrow(()-> new Exception400("Could not found product for " + id)));
    }
}