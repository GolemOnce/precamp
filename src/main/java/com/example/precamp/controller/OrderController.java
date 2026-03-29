package com.example.precamp.controller;

import com.example.precamp.domain.Order;
import com.example.precamp.dto.OrderCreateResponseDto;
import com.example.precamp.dto.OrderRequestDto;
import com.example.precamp.dto.OrderResponseDto;
import com.example.precamp.service.OrderService;
import com.example.precamp.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final ProductService productService;

    // 등록
    @PostMapping("/orders/new")
    public ResponseEntity<?> save (@RequestBody OrderRequestDto request) {
        try {
            Order order = orderService.saveOrder(request);
            return ResponseEntity.status(201).body(new OrderCreateResponseDto(order));
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 단일 조회
    @GetMapping("/orders/{id}")
    public ResponseEntity<OrderResponseDto> findOrderById (@PathVariable Long id) {
        Order order = orderService.findOrderById(id);
        return ResponseEntity.ok(new OrderResponseDto(order));
    }


    // 목록 조회(도전)
    @GetMapping("/orders")
    public ResponseEntity<List<OrderResponseDto>> findAllOrders(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<OrderResponseDto> orders = orderService.findAllOrders(page, size).stream()
                .map(OrderResponseDto::new).toList();
        return ResponseEntity.ok(orders);
    }
}
