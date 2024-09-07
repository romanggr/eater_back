package com.eater.eater.controller;

import com.eater.eater.dto.auth.UpdatePasswordRequest;
import com.eater.eater.dto.restaurantOwner.*;
import com.eater.eater.model.restaurantOwner.Restaurant;
import com.eater.eater.service.restaurantOwner.RestaurantOwnerService;
import com.eater.eater.service.restaurantOwner.RestaurantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/restaurantOwner")
public class RestaurantOwnerController {
private final RestaurantOwnerService restaurantOwnerService;
private final RestaurantService restaurantService;

    public RestaurantOwnerController(RestaurantOwnerService restaurantOwnerService, RestaurantService restaurantService) {
        this.restaurantOwnerService = restaurantOwnerService;
        this.restaurantService = restaurantService;
    }

    @GetMapping("/getRestaurantOwner")
        public ResponseEntity<RestaurantOwnerDTO> getRestaurantOwner(){
        RestaurantOwnerDTO response = restaurantOwnerService.getRestaurantOwner();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/createRestaurant")
    public ResponseEntity<RestaurantDTO> createRestaurant(@RequestBody RestaurantDTO request){
        RestaurantDTO response = restaurantOwnerService.createRestaurant(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/updateRestaurantOwner")
    public ResponseEntity<RestaurantOwnerDTO> updateRestaurantOwner(@RequestBody UpdateRestaurantOwnerRequest request) {
        RestaurantOwnerDTO response = restaurantOwnerService.updateRestaurantOwner(request);
        return ResponseEntity.ok(response);
    }


    @PutMapping("/updatePassword")
    public ResponseEntity<RestaurantOwnerDTO> updatePassword(@RequestBody UpdatePasswordRequest request) {
        RestaurantOwnerDTO response = restaurantOwnerService.updatePassword(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/updateRestaurant")
    public ResponseEntity<RestaurantDTO> updateRestaurant(@RequestBody UpdateRestaurantRequest request) {
        RestaurantDTO response = restaurantService.updateRestaurant(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/createDish")
    public ResponseEntity<RestaurantDishDTO> createDish(@RequestBody RestaurantDishRequest request){
        RestaurantDishDTO response = restaurantService.createDish(request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/deleteDish/{id}")
    public ResponseEntity<Long> deleteDish(@PathVariable Long id){
        Long response = restaurantService.deleteDish(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/updateDish")
    public ResponseEntity<RestaurantDishDTO> createDish(@RequestBody RestaurantDishUpdateRequest request){
        RestaurantDishDTO response = restaurantService.updateDish(request);
        return ResponseEntity.ok(response);
    }
}
