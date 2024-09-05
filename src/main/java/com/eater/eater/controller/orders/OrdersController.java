package com.eater.eater.controller.orders;

import com.eater.eater.service.orders.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class OrdersController {
    private final OrdersService ordersService;

    @Autowired
    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
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
