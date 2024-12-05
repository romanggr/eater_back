package com.eater.eater.admin.controller;

import com.eater.eater.admin.dto.order.OrderDTO;
import com.eater.eater.admin.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/admin")
public class AdminOrderController {
    private final OrderService orderService;

    @GetMapping("/orders")
    public ResponseEntity<List<OrderDTO>> getOrders() {
        List<OrderDTO> response = orderService.getOrders();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<OrderDTO> getOrders(@PathVariable Long id) {
        OrderDTO response = orderService.getOrderById(id);
        return ResponseEntity.ok(response);
    }
}
