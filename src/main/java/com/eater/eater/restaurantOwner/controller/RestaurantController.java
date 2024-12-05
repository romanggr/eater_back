package com.eater.eater.restaurantOwner.controller;

import com.eater.eater.restaurantOwner.dto.RestaurantDishDTO;
import com.eater.eater.restaurantOwner.dto.RestaurantDishRequest;
import com.eater.eater.restaurantOwner.dto.RestaurantDishUpdateRequest;
import com.eater.eater.restaurantOwner.service.restaurant.RestaurantServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/restaurantOwner")
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantServiceImpl restaurantService;

    @GetMapping("/dishes")
    public ResponseEntity<List<RestaurantDishDTO>> getDishes(){
        List<RestaurantDishDTO> response = restaurantService.getDishes();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/createDish")
    public ResponseEntity<RestaurantDishDTO> createDish(@ModelAttribute RestaurantDishRequest request){
        RestaurantDishDTO response = restaurantService.createDish(request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/deleteDish/{id}")
    public ResponseEntity<Long> deleteDish(@PathVariable Long id){
        Long response = restaurantService.deleteDish(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/updateDish")
    public ResponseEntity<RestaurantDishDTO> createDish(@ModelAttribute RestaurantDishUpdateRequest request){
        RestaurantDishDTO response = restaurantService.updateDish(request);
        return ResponseEntity.ok(response);
    }
}
