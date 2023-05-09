package com.example.kakao.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductJPARepository productRepository;

    public Optional<Product> findById(int productId) {
        return productRepository.findByProductId(productId);
    }

    public List<Product> findAll(int page) {
        Pageable pageable = PageRequest.of(page,9);
        Page<Product> pageContent = productRepository.findAll(pageable);
        return pageContent.getContent();
    }
}
