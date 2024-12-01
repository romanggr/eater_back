package com.eater.eater.controller;

import com.eater.eater.dto.orders.OrderDTORestaurant;
import com.eater.eater.dto.orders.OrderHistoryRestaurantDTO;
import com.eater.eater.dto.restaurantOwner.*;
import com.eater.eater.service.restaurantOwner.RestaurantOwnerServiceImpl;
import com.eater.eater.service.restaurantOwner.RestaurantServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/restaurantOwner")
public class RestaurantOwnerController {
private final RestaurantOwnerServiceImpl restaurantOwnerServiceImpl;
private final RestaurantServiceImpl restaurantServiceImpl;


    public RestaurantOwnerController(RestaurantOwnerServiceImpl restaurantOwnerServiceImpl, RestaurantServiceImpl restaurantServiceImpl) {
        this.restaurantOwnerServiceImpl = restaurantOwnerServiceImpl;
        this.restaurantServiceImpl = restaurantServiceImpl;

    }

    @GetMapping("/getRestaurantOwner")
        public ResponseEntity<RestaurantOwnerDTO> getRestaurantOwner(){
        RestaurantOwnerDTO response = restaurantOwnerServiceImpl.getRestaurantOwner();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/createRestaurant")
    public ResponseEntity<RestaurantDTO> createRestaurant(@ModelAttribute CreateRestaurantRequest request){
        RestaurantDTO response = restaurantOwnerServiceImpl.createRestaurant(request);
        return ResponseEntity.ok(response);
    }


    @PutMapping("/updateRestaurant")
    public ResponseEntity<RestaurantDTO> updateRestaurant(@ModelAttribute UpdateRestaurantRequest request) {
        RestaurantDTO response = restaurantServiceImpl.updateRestaurant(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/dishes")
    public ResponseEntity<List<RestaurantDishDTO>> getDishes(){
        List<RestaurantDishDTO> response = restaurantServiceImpl.getDishes();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/createDish")
    public ResponseEntity<RestaurantDishDTO> createDish(@ModelAttribute RestaurantDishRequest request){
        RestaurantDishDTO response = restaurantServiceImpl.createDish(request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/deleteDish/{id}")
    public ResponseEntity<Long> deleteDish(@PathVariable Long id){
        Long response = restaurantServiceImpl.deleteDish(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/updateDish")
    public ResponseEntity<RestaurantDishDTO> createDish(@ModelAttribute RestaurantDishUpdateRequest request){
        RestaurantDishDTO response = restaurantServiceImpl.updateDish(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/newOrders")
    public ResponseEntity<List<OrderDTORestaurant>> getNewOrders(){
        List<OrderDTORestaurant> response = restaurantServiceImpl.getNewOrders();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/setOrderCooked/{id}")
    public ResponseEntity<OrderDTORestaurant> setOrderCooked(@PathVariable Long id){
        OrderDTORestaurant response = restaurantServiceImpl.setOrderCooked(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/ordersHistory")
    public ResponseEntity<List<OrderHistoryRestaurantDTO>> getOrdersHistory(){
        List<OrderHistoryRestaurantDTO> response = restaurantServiceImpl.getOrdersHistory();

        return ResponseEntity.ok(response);
    }
}
