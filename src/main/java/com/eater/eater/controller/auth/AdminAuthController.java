package com.eater.eater.controller.auth;

import com.eater.eater.dto.admin.AdminDTO;
import com.eater.eater.dto.admin.UpdateAdminRequest;
import com.eater.eater.dto.auth.*;
import com.eater.eater.service.admin.AdminServiceImpl;
import com.eater.eater.service.auth.AdminAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/auth/admin")
@RestController
@RequiredArgsConstructor
public class AdminAuthController {
    private final AdminAuthService adminAuthService;
    private final AdminServiceImpl adminService;


    @PostMapping("/signup")
    public ResponseEntity<AuthResponse<AdminDTO>> signup(@RequestBody AdminRegistrationRequest courierRegistrationRequest) {
        AuthResponse<AdminDTO> response = adminAuthService.signUp(courierRegistrationRequest);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse<AdminDTO>> login(@RequestBody LoginRequest loginRequest) {
        AuthResponse<AdminDTO> response = adminAuthService.login(loginRequest);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/update")
    public ResponseEntity<AuthResponse<AdminDTO>> update(@RequestBody UpdateAdminRequest courierDTO){
        AuthResponse<AdminDTO> response = adminAuthService.update(courierDTO);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/updatePassword")
    public ResponseEntity<AuthResponse<AdminDTO>> updatePassword(@RequestBody UpdatePasswordRequest request) {
        AuthResponse<AdminDTO> response = adminAuthService.updatePassword(request);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/fakeData")
    public ResponseEntity<String> generateFakeData() {
        adminService.generateFakeData();
        return ResponseEntity.ok("Successfully added");
    }

}


