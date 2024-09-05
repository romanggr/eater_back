package com.eater.eater.controller.restaurantOwner;

import com.eater.eater.model.restaurantOwner.RestaurantOwner;
import com.eater.eater.service.restaurantOwner.RestaurantOwnerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/restaurantOwner")
public class RestaurantOwnerController {
//private final RestaurantOwnerService restaurantOwnerService;
//
//    public RestaurantOwnerController(RestaurantOwnerService restaurantOwnerService) {
//        this.restaurantOwnerService = restaurantOwnerService;
//    }

//    @GetMapping("/getAll")
//        public ResponseEntity<List<RestaurantOwner>> getAll() {
//        List<RestaurantOwner> users = restaurantOwnerService.getAll();
//
//        return ResponseEntity.ok(users);
//    }
}
