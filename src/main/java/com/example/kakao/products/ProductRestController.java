package com.example.kakao.products;

import com.example.kakao.errors.NotFoundException;
import com.example.kakao.utils.ApiUtils.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.kakao.utils.ApiUtils.success;
import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/products")
public class ProductRestController {

    @Autowired
    private ProductService productService;

    /**
     * [판매자&구매자 공통 기능] 전체 상품 조회
     * @return
     * 전체 상품 리스트를 반환
     */
    @GetMapping
    public ApiResult<List<ProductDto>> findAll(@RequestParam int page) {
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
                .orElseThrow(()-> new NotFoundException("Could not found product for " + id)));
    }
}