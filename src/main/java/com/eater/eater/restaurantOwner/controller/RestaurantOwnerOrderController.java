package com.eater.eater.restaurantOwner.controller;

import com.eater.eater.restaurantOwner.dto.order.OrderDTORestaurant;
import com.eater.eater.restaurantOwner.dto.order.OrderHistoryRestaurantDTO;
import com.eater.eater.restaurantOwner.service.order.RestaurantRestaurantOrderServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/restaurantOwner")
@RequiredArgsConstructor
public class RestaurantOwnerOrderController {
    private final RestaurantRestaurantOrderServiceImpl orderService;

    @GetMapping("/newOrders")
    public ResponseEntity<List<OrderDTORestaurant>> getNewOrders(){
        List<OrderDTORestaurant> response = orderService.getNewOrders();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/setOrderCooked/{id}")
    public ResponseEntity<OrderDTORestaurant> setOrderCooked(@PathVariable Long id){
        OrderDTORestaurant response = orderService.setOrderCooked(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/ordersHistory")
    public ResponseEntity<List<OrderHistoryRestaurantDTO>> getOrdersHistory(){
        List<OrderHistoryRestaurantDTO> response = orderService.getOrdersHistory();

        return ResponseEntity.ok(response);
    }
}
