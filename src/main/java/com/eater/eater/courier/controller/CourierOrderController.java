package com.eater.eater.courier.controller;

import com.eater.eater.courier.dto.order.OrderDTOCourier;
import com.eater.eater.courier.dto.order.OrderHistoryCourierDTO;
import com.eater.eater.courier.service.order.CourierOrderServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/courier")
@RequiredArgsConstructor
public class CourierOrderController {
    private final CourierOrderServiceImpl orderService;

    @GetMapping("/order")
    public ResponseEntity<OrderDTOCourier> getOrder() {
        OrderDTOCourier response = orderService.getOrder();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/setOrderDelivered/{id}")
    public ResponseEntity<Long> setOrderDelivered(@PathVariable Long id) {
        Long deliveredId = orderService.setOrderDelivered(id);
        return ResponseEntity.ok(deliveredId);
    }

    @GetMapping("/getOrdersHistory")
    public ResponseEntity<List<OrderHistoryCourierDTO>> getOrdersHistory() {
        List<OrderHistoryCourierDTO> response = orderService.getOrdersHistory();

        return ResponseEntity.ok(response);
    }
}
