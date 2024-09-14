package com.eater.eater.controller;

import com.eater.eater.dto.auth.LoginResponse;
import com.eater.eater.dto.auth.UpdatePasswordRequest;
import com.eater.eater.dto.restaurantOwner.*;
import com.eater.eater.service.auth.AuthService;
import com.eater.eater.service.restaurantOwner.RestaurantOwnerService;
import com.eater.eater.service.restaurantOwner.RestaurantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/restaurantOwner")
public class RestaurantOwnerController {
private final RestaurantOwnerService restaurantOwnerService;
private final RestaurantService restaurantService;
    private final AuthService authService;

    public RestaurantOwnerController(RestaurantOwnerService restaurantOwnerService, RestaurantService restaurantService, AuthService authService) {
        this.restaurantOwnerService = restaurantOwnerService;
        this.restaurantService = restaurantService;
        this.authService = authService;
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
    public ResponseEntity<LoginResponse> updateRestaurantOwner(@RequestBody UpdateRestaurantOwnerRequest request) {
        LoginResponse response = authService.updateRestaurantOwner(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/updatePassword")
    public ResponseEntity<LoginResponse> updatePassword(@RequestBody UpdatePasswordRequest request) {
        LoginResponse response = authService.updateRestaurantOwnerPassword(request);
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
