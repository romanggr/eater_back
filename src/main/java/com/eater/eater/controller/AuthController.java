package com.eater.eater.controller;

import com.eater.eater.dto.auth.*;
import com.eater.eater.service.auth.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/auth")
@RestController
public class AuthController {
    private final AuthService authService;
    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("/courier/signup")
    public ResponseEntity<LoginResponse> courierRegister(@RequestBody CourierRegistrationRequest courierRegistrationRequest) {
        LoginResponse jwt = authService.courierSignup(courierRegistrationRequest);

        return ResponseEntity.ok(jwt);
    }

    @PostMapping("/admin/signup")
    public ResponseEntity<LoginResponse> adminRegister(@RequestBody AdminRegistrationRequest adminRegistrationRequest) {
        LoginResponse jwt = authService.adminSignup(adminRegistrationRequest);

        return ResponseEntity.ok(jwt);
    }


    @PostMapping("/client/signup")
    public ResponseEntity<LoginResponse> clientRegister(@RequestBody ClientRegistrationRequest clientRegistrationRequest) {
        LoginResponse jwt = authService.clientSignup(clientRegistrationRequest);

        return ResponseEntity.ok(jwt);
    }

    @PostMapping("/restaurantOwner/signup")
    public ResponseEntity<LoginResponse> restaurantOwnerRegister(@RequestBody RestaurantOwnerRegistrationRequest restaurantOwnerRegistrationRequest) {
        LoginResponse jwt = authService.restaurantOwnerSignup(restaurantOwnerRegistrationRequest);

        return ResponseEntity.ok(jwt);
    }


    @PostMapping("/courier/login")
    public ResponseEntity<LoginResponse> courierAuthenticate(@RequestBody LoginRequest loginRequest) {
        LoginResponse jwt = authService.courierAuthenticate(loginRequest);

        return ResponseEntity.ok(jwt);
    }

    @PostMapping("/client/login")
    public ResponseEntity<LoginResponse> clientAuthenticate(@RequestBody LoginRequest loginRequest) {
        LoginResponse jwt = authService.clientAuthenticate(loginRequest);

        return ResponseEntity.ok(jwt);
    }

    @PostMapping("/admin/login")
    public ResponseEntity<LoginResponse> adminAuthenticate(@RequestBody LoginRequest loginRequest) {
        LoginResponse jwt = authService.courierAuthenticate(loginRequest);

        return ResponseEntity.ok(jwt);
    }

    @PostMapping("/restaurantOwner/login")
    public ResponseEntity<LoginResponse> restaurantOwnerAuthenticate(@RequestBody LoginRequest loginRequest) {
        LoginResponse jwt = authService.courierAuthenticate(loginRequest);

        return ResponseEntity.ok(jwt);
    }



//    @GetMapping("/me")
//    public ResponseEntity<CourierDto> authenticatedUser() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        Courier currentCourier = (Courier) authentication.getPrincipal();
//        CourierDto currentUserDto = new CourierDto();
//        currentUserDto.setId(currentCourier.getId());
//        currentUserDto.setName(currentCourier.getName());
//        currentUserDto.setEmail(currentCourier.getEmail());
//        return ResponseEntity.ok(currentUserDto);
//    }

}