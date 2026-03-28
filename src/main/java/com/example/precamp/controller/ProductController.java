package com.example.precamp.controller;

import com.example.precamp.domain.Product;
import com.example.precamp.dto.ProductRequestDto;
import com.example.precamp.dto.ProductResponseDto;
import com.example.precamp.service.ProductService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // 등록
    @PostMapping("/products")
    public ResponseEntity<ProductResponseDto> create(@RequestBody ProductRequestDto request) {
        Product product = productService.saveProduct(request);
        return ResponseEntity.status(201).body(new ProductResponseDto(product));
    }

    // 수정
    @PatchMapping("/products/{id}")
    public ResponseEntity<ProductResponseDto> update(@PathVariable Long id, @RequestBody ProductRequestDto request) {
        Product product = productService.updateProduct(id, request);
        return ResponseEntity.ok(new ProductResponseDto(product));
    }

    // 삭제
    @DeleteMapping("/products/{id}")
    public ResponseEntity<ProductResponseDto> delete(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    // 목록 조회
    @GetMapping("/products")
    public ResponseEntity<List<ProductResponseDto>> list() {
        List<ProductResponseDto> products = productService.findAll().stream()
                .map(ProductResponseDto::new)
                .toList();
        return ResponseEntity.ok(products);
    }

    // 단일 조회
    @GetMapping("/products/{id}")
    public ResponseEntity<ProductResponseDto> findById(@PathVariable Long id) {
        Product product = productService.findById(id);
        return ResponseEntity.ok(new ProductResponseDto(product));
    }
}
