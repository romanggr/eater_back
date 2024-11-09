package com.eater.eater.controller.auth;

import com.eater.eater.dto.auth.*;
import com.eater.eater.dto.restaurantOwner.RestaurantOwnerDTO;
import com.eater.eater.dto.restaurantOwner.UpdateRestaurantOwnerRequest;
import com.eater.eater.service.auth.RestaurantOwnerAuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/auth/restaurantOwner")
@RestController
public class RestaurantOwnerAuthController {
    private final RestaurantOwnerAuthService restaurantOwnerAuthService;

    public RestaurantOwnerAuthController(RestaurantOwnerAuthService restaurantOwnerAuthService) {
        this.restaurantOwnerAuthService = restaurantOwnerAuthService;
    }

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse<RestaurantOwnerDTO>> signup(@RequestBody RestaurantOwnerRegistrationRequest courierRegistrationRequest) {
        AuthResponse<RestaurantOwnerDTO> response = restaurantOwnerAuthService.signUp(courierRegistrationRequest);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse<RestaurantOwnerDTO>> login(@RequestBody LoginRequest loginRequest) {
        AuthResponse<RestaurantOwnerDTO> response = restaurantOwnerAuthService.login(loginRequest);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/update")
    public ResponseEntity<AuthResponse<RestaurantOwnerDTO>> update(@RequestBody UpdateRestaurantOwnerRequest request){
        AuthResponse<RestaurantOwnerDTO> response = restaurantOwnerAuthService.update(request);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/updatePassword")
    public ResponseEntity<AuthResponse<RestaurantOwnerDTO>> updatePassword(@RequestBody UpdatePasswordRequest request) {
        AuthResponse<RestaurantOwnerDTO> response = restaurantOwnerAuthService.updatePassword(request);

        return ResponseEntity.ok(response);
    }

}
