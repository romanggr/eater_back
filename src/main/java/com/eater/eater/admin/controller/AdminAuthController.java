package com.eater.eater.admin.controller;

import com.eater.eater.DTO.auth.AuthResponse;
import com.eater.eater.DTO.auth.LoginRequest;
import com.eater.eater.DTO.auth.UpdatePasswordRequest;
import com.eater.eater.admin.dto.AdminDTO;
import com.eater.eater.admin.dto.auth.UpdateAdminRequest;
import com.eater.eater.admin.dto.auth.AdminRegistrationRequest;
import com.eater.eater.admin.service.auth.AdminAuthServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/auth/admin")
@RestController
@RequiredArgsConstructor
public class AdminAuthController {
    private final AdminAuthServiceImpl adminAuthServiceImpl;


    @PostMapping("/signup")
    public ResponseEntity<AuthResponse<AdminDTO>> signup(@RequestBody AdminRegistrationRequest courierRegistrationRequest) {
        AuthResponse<AdminDTO> response = adminAuthServiceImpl.signUp(courierRegistrationRequest);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse<AdminDTO>> login(@RequestBody LoginRequest loginRequest) {
        AuthResponse<AdminDTO> response = adminAuthServiceImpl.login(loginRequest);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/update")
    public ResponseEntity<AuthResponse<AdminDTO>> update(@RequestBody UpdateAdminRequest courierDTO){
        AuthResponse<AdminDTO> response = adminAuthServiceImpl.update(courierDTO);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/updatePassword")
    public ResponseEntity<AuthResponse<AdminDTO>> updatePassword(@RequestBody UpdatePasswordRequest request) {
        AuthResponse<AdminDTO> response = adminAuthServiceImpl.updatePassword(request);

        return ResponseEntity.ok(response);
    }

}


