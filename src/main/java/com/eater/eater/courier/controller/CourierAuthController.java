package com.eater.eater.courier.controller;

import com.eater.eater.DTO.auth.AuthResponse;
import com.eater.eater.courier.dto.auth.CourierRegistrationRequest;
import com.eater.eater.DTO.auth.LoginRequest;
import com.eater.eater.DTO.auth.UpdatePasswordRequest;
import com.eater.eater.courier.dto.CourierDTO;
import com.eater.eater.courier.dto.auth.UpdateCourierRequest;

import com.eater.eater.courier.service.auth.CourierAuthServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/auth/courier")
@RestController
@RequiredArgsConstructor
public class CourierAuthController {
    private final CourierAuthServiceImpl courierAuthService;


    @PostMapping("/signup")
    public ResponseEntity<AuthResponse<CourierDTO>> signup(@ModelAttribute CourierRegistrationRequest courierRegistrationRequest) {
        AuthResponse<CourierDTO> response = courierAuthService.signUp(courierRegistrationRequest);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse<CourierDTO>> login(@RequestBody LoginRequest loginRequest) {
        AuthResponse<CourierDTO> response = courierAuthService.login(loginRequest);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/update")
    public ResponseEntity<AuthResponse<CourierDTO>> update(@ModelAttribute UpdateCourierRequest courierRequest){
        AuthResponse<CourierDTO> response = courierAuthService.update(courierRequest);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/updatePassword")
    public ResponseEntity<AuthResponse<CourierDTO>> updatePassword(@RequestBody UpdatePasswordRequest request) {
        AuthResponse<CourierDTO> response = courierAuthService.updatePassword(request);

        return ResponseEntity.ok(response);
    }



}
