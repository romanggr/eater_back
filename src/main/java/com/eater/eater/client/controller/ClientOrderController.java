package com.eater.eater.client.controller;

import com.eater.eater.client.dto.order.CreateOrderRequest;
import com.eater.eater.client.dto.order.OrderDTOClient;
import com.eater.eater.client.dto.order.OrderHistoryClientDTO;
import com.eater.eater.client.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/client")
@RequiredArgsConstructor
public class ClientOrderController {
    private final OrderService orderService;

    @PostMapping("/createOrder")
    public ResponseEntity<OrderDTOClient> createOrder(@RequestBody CreateOrderRequest request) {
        OrderDTOClient response = orderService.createOrder(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/confirmOrder/{id}")
    public ResponseEntity<OrderDTOClient> confirmOrder(@PathVariable Long id) {
        OrderDTOClient response = orderService.confirmOrder(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getOrderHistory")
    public ResponseEntity<List<OrderHistoryClientDTO>> getClientOrderHistory() {
        List<OrderHistoryClientDTO> response = orderService.getClientOrderHistory();
        return ResponseEntity.ok(response);
    }
}
