package com.example.precamp.service;

import com.example.precamp.domain.Product;
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
    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    @Transactional
    public void updateProduct(Long productId, String name, int price, int stock) {
        Product findProduct = productRepository.findById(productId);
        findProduct.setName(name);
        findProduct.setPrice(price);
        findProduct.setStock(stock);
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
