package com.example.precamp.service;

import com.example.precamp.domain.Product;
import com.example.precamp.dto.ProductRequestDto;
import com.example.precamp.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        Product findProduct = productRepository.findById(productId);
        findProduct.setName(request.getName());
        findProduct.setPrice(request.getPrice());
        findProduct.setStock(request.getStock());
        return findProduct;
    }

    @Transactional
    public void deleteProduct(Long productId) {
        Product findProduct = productRepository.findById(productId);
        productRepository.remove(findProduct);
    }

    @Transactional(readOnly = true)
    public Product findById(Long productId) {
        return productRepository.findById(productId);
    }

    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return productRepository.findAll();
    }
}
