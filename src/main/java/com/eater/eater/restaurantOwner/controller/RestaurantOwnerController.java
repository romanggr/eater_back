package com.eater.eater.restaurantOwner.controller;

import com.eater.eater.restaurantOwner.dto.*;
import com.eater.eater.restaurantOwner.service.owner.RestaurantOwnerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/restaurantOwner")
@RequiredArgsConstructor
public class RestaurantOwnerController {
    private final RestaurantOwnerServiceImpl restaurantOwnerServiceImpl;


    @PostMapping("/createRestaurant")
    public ResponseEntity<RestaurantDTO> createRestaurant(@ModelAttribute CreateRestaurantRequest request) {
        RestaurantDTO response = restaurantOwnerServiceImpl.createRestaurant(request);
        return ResponseEntity.ok(response);
    }


    @PutMapping("/updateRestaurant")
    public ResponseEntity<RestaurantDTO> updateRestaurant(@ModelAttribute UpdateRestaurantRequest request) {
        RestaurantDTO response = restaurantOwnerServiceImpl.updateRestaurant(request);
        return ResponseEntity.ok(response);
    }


}
