package com.example.kakao.products;

import com.example.kakao._core.errors.exception.Exception404;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductJPARepository productRepository;

    public ProductResponse.FindByIdDTO findById(int productId) {
        Product productPS = productRepository.findByProductId(productId).orElseThrow(
                () -> new Exception404("해당 상품을 찾을 수 없습니다 : "+productId)
        );
        return new ProductResponse.FindByIdDTO(productPS);
    }

    public ProductResponse.FindAllDTO findAll(int page) {
        Pageable pageable = PageRequest.of(page,9);
        Page<Product> pageContent = productRepository.findAll(pageable);
        return new ProductResponse.FindAllDTO(pageContent.getContent());
    }
}
