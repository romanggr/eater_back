package com.eater.eater.controller;

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
    public ResponseEntity<RestaurantDTO> createRestaurant(@RequestBody RestaurantDTO request){
        RestaurantDTO response = restaurantOwnerServiceImpl.createRestaurant(request);
        return ResponseEntity.ok(response);
    }


    @PutMapping("/updateRestaurant")
    public ResponseEntity<RestaurantDTO> updateRestaurant(@RequestBody UpdateRestaurantRequest request) {
        RestaurantDTO response = restaurantServiceImpl.updateRestaurant(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/dishes")
    public ResponseEntity<List<RestaurantDishDTO>> getDishes(){
        List<RestaurantDishDTO> response = restaurantServiceImpl.getDishes();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/createDish")
    public ResponseEntity<RestaurantDishDTO> createDish(@RequestBody RestaurantDishRequest request){
        RestaurantDishDTO response = restaurantServiceImpl.createDish(request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/deleteDish/{id}")
    public ResponseEntity<Long> deleteDish(@PathVariable Long id){
        Long response = restaurantServiceImpl.deleteDish(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/updateDish")
    public ResponseEntity<RestaurantDishDTO> createDish(@RequestBody RestaurantDishUpdateRequest request){
        RestaurantDishDTO response = restaurantServiceImpl.updateDish(request);
        return ResponseEntity.ok(response);
    }
}
