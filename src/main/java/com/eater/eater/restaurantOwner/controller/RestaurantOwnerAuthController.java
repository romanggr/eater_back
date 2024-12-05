package com.eater.eater.restaurantOwner.controller;

import com.eater.eater.restaurantOwner.dto.RestaurantOwnerDTO;
import com.eater.eater.restaurantOwner.dto.UpdateRestaurantOwnerRequest;
import com.eater.eater.DTO.auth.AuthResponse;
import com.eater.eater.DTO.auth.LoginRequest;
import com.eater.eater.DTO.auth.UpdatePasswordRequest;
import com.eater.eater.restaurantOwner.dto.RestaurantOwnerRegistrationRequest;
import com.eater.eater.restaurantOwner.service.auth.RestaurantOwnerAuthServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/auth/restaurantOwner")
@RestController
@RequiredArgsConstructor
public class RestaurantOwnerAuthController {
    private final RestaurantOwnerAuthServiceImpl restaurantOwnerAuthServiceImpl;


    @PostMapping("/signup")
    public ResponseEntity<AuthResponse<RestaurantOwnerDTO>> signup(@RequestBody RestaurantOwnerRegistrationRequest courierRegistrationRequest) {
        AuthResponse<RestaurantOwnerDTO> response = restaurantOwnerAuthServiceImpl.signUp(courierRegistrationRequest);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse<RestaurantOwnerDTO>> login(@RequestBody LoginRequest loginRequest) {
        AuthResponse<RestaurantOwnerDTO> response = restaurantOwnerAuthServiceImpl.login(loginRequest);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/update")
    public ResponseEntity<AuthResponse<RestaurantOwnerDTO>> update(@RequestBody UpdateRestaurantOwnerRequest request){
        AuthResponse<RestaurantOwnerDTO> response = restaurantOwnerAuthServiceImpl.update(request);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/updatePassword")
    public ResponseEntity<AuthResponse<RestaurantOwnerDTO>> updatePassword(@RequestBody UpdatePasswordRequest request) {
        AuthResponse<RestaurantOwnerDTO> response = restaurantOwnerAuthServiceImpl.updatePassword(request);

        return ResponseEntity.ok(response);
    }

}
