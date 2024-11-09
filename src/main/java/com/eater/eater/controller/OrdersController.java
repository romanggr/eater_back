package com.eater.eater.controller;

import com.eater.eater.service.orders.OrdersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class OrdersController {
    private final OrdersServiceImpl ordersServiceImpl;

    @Autowired
    public OrdersController(OrdersServiceImpl ordersServiceImpl) {
        this.ordersServiceImpl = ordersServiceImpl;
    }

    // Get All
//    @GetMapping("/orders")
//    public List<OrdersDTO> getAllOrders() {
//        return ordersService.getAllOrders();
//    }
//
//    // Get One
//    @GetMapping("/orders/{id}")
//    public Optional<OrdersDTO> getOrder(@PathVariable Long id) {
//        return ordersService.getOrder(id);
//    }
//
//    // Post
//    @PostMapping("/order")
//    public OrdersDTO addOrder(@RequestBody OrdersDTO ordersDTO) {
//        Orders orders = ordersService.addOrder(ordersDTO);
//        return OrdersMapper.toDTO(orders);
//    }
}
