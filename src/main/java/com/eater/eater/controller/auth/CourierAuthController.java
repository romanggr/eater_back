package com.eater.eater.controller.auth;

import com.eater.eater.dto.auth.AuthResponse;
import com.eater.eater.dto.auth.CourierRegistrationRequest;
import com.eater.eater.dto.auth.LoginRequest;
import com.eater.eater.dto.auth.UpdatePasswordRequest;
import com.eater.eater.dto.courier.CourierDTO;
import com.eater.eater.dto.courier.UpdateCourierRequest;
import com.eater.eater.service.auth.CourierAuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/auth/courier")
@RestController
public class CourierAuthController {
    private final CourierAuthService courierAuthService;

    public CourierAuthController(CourierAuthService courierAuthService) {
        this.courierAuthService = courierAuthService;
    }

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse<CourierDTO>> signup(@RequestBody CourierRegistrationRequest courierRegistrationRequest) {
        AuthResponse<CourierDTO> response = courierAuthService.signUp(courierRegistrationRequest);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse<CourierDTO>> login(@RequestBody LoginRequest loginRequest) {
        AuthResponse<CourierDTO> response = courierAuthService.login(loginRequest);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/update")
    public ResponseEntity<AuthResponse<CourierDTO>> update(@RequestBody UpdateCourierRequest courierDTO){
        AuthResponse<CourierDTO> response = courierAuthService.update(courierDTO);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/updatePassword")
    public ResponseEntity<AuthResponse<CourierDTO>> updatePassword(@RequestBody UpdatePasswordRequest request) {
        AuthResponse<CourierDTO> response = courierAuthService.updatePassword(request);

        return ResponseEntity.ok(response);
    }

}
