package com.example.precamp.service;

import com.example.precamp.domain.Product;
import com.example.precamp.dto.ProductRequestDto;
import com.example.precamp.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public Product saveProduct(ProductRequestDto request) {
        Product product = new Product();
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());
        productRepository.save(product);
        return product;
    }

    @Transactional
    public Product updateProduct(Long productId, ProductRequestDto request) {
        Product findProduct = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("상품이 없습니다."));
        findProduct.setName(request.getName());
        findProduct.setPrice(request.getPrice());
        findProduct.setStock(request.getStock());
        return findProduct;
    }

    @Transactional
    public void deleteProduct(Long productId) {
        Product findProduct = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("상품이 없습니다."));
        productRepository.delete(findProduct);
    }

    @Transactional(readOnly = true)
    public Product findById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("상품이 없습니다."));
    }

    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return productRepository.findAll();
    }
}
