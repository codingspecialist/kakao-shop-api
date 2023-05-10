package com.example.kakao.products;

import com.example.kakao._core.utils.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ProductRestController {

    private final ProductService productService;

    /**
     * [판매자&구매자 공통 기능] 전체 상품 조회
     * @return
     * 전체 상품 리스트를 반환
     */
    @GetMapping("/products")
    public ResponseEntity<?> findAll(@RequestParam(defaultValue = "0") int page) {
        ProductResponse.FindAllDTO findAllDTO = productService.findAll(page);
        ApiUtils.ApiResult<?> apiResult = ApiUtils.success(findAllDTO);
        return ResponseEntity.ok(apiResult);
    }

    /**
     * [판매자&구매자 공통 기능] 단일 상품 조회
     * @param id // 상품 id
     * 상품 id를 받아서
     * @return
     * 해당 상품 정보를 반환
     */
    @GetMapping("/products/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        ProductResponse.FindByIdDTO findByIdDTO = productService.findById(id);
        ApiUtils.ApiResult<?> apiResult = ApiUtils.success(findByIdDTO);
        return ResponseEntity.ok(apiResult);
    }
}