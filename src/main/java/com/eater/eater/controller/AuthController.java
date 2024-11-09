//package com.eater.eater.controller;
//
//import com.eater.eater.dto.admin.AdminDTO;
//import com.eater.eater.dto.auth.*;
//import com.eater.eater.dto.client.ClientDTO;
//import com.eater.eater.dto.restaurantOwner.RestaurantOwnerDTO;
//import com.eater.eater.service.auth.CourierAuthService;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RequestMapping("/api/v1/auth")
//@RestController
//public class AuthController {
//    private final AuthService authService;
//    private final CourierAuthService courierAuthService;
//
//    public AuthController(AuthServiceImpl authService, CourierAuthService courierAuthService) {
//        this.authService = authService;
//        this.courierAuthService = courierAuthService;
//    }
//
//
//
//
//    @PostMapping("/admin/signup")
//    public ResponseEntity<AuthResponse<AdminDTO>> adminRegister(@RequestBody AdminRegistrationRequest adminRegistrationRequest) {
//        AuthResponse<AdminDTO> response = authService.adminSignup(adminRegistrationRequest);
//
//        return ResponseEntity.ok(response);
//    }
//
//
//    @PostMapping("/client/signup")
//    public ResponseEntity<AuthResponse<ClientDTO>> clientRegister(@RequestBody ClientRegistrationRequest clientRegistrationRequest) {
//        AuthResponse<ClientDTO> response = authService.clientSignup(clientRegistrationRequest);
//
//        return ResponseEntity.ok(response);
//    }
//
//    @PostMapping("/restaurantOwner/signup")
//    public ResponseEntity<AuthResponse<RestaurantOwnerDTO>> restaurantOwnerRegister(@RequestBody RestaurantOwnerRegistrationRequest restaurantOwnerRegistrationRequest) {
//        AuthResponse<RestaurantOwnerDTO> response = authService.restaurantOwnerSignup(restaurantOwnerRegistrationRequest);
//
//        return ResponseEntity.ok(response);
//    }
//
//
//
//    @PostMapping("/client/login")
//    public ResponseEntity<AuthResponse<ClientDTO>> clientAuthenticate(@RequestBody LoginRequest loginRequest) {
//        AuthResponse<ClientDTO> response = authService.clientAuthenticate(loginRequest);
//
//        return ResponseEntity.ok(response);
//    }
//
//    @PostMapping("/admin/login")
//    public ResponseEntity<AuthResponse<AdminDTO>> adminAuthenticate(@RequestBody LoginRequest loginRequest) {
//        AuthResponse<AdminDTO> response = authService.adminAuthenticate(loginRequest);
//
//        return ResponseEntity.ok(response);
//    }
//
//    @PostMapping("/restaurantOwner/login")
//    public ResponseEntity<AuthResponse<RestaurantOwnerDTO>> restaurantOwnerAuthenticate(@RequestBody LoginRequest loginRequest) {
//        AuthResponse<RestaurantOwnerDTO> response = authService.restaurantOwnerAuthenticate(loginRequest);
//
//        return ResponseEntity.ok(response);
//    }
//
//
//}