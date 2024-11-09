package com.eater.eater.controller.auth;

import com.eater.eater.dto.auth.*;
import com.eater.eater.dto.client.ClientDTO;
import com.eater.eater.dto.client.UpdateClientRequest;
import com.eater.eater.service.auth.ClientAuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/auth/client")
@RestController
public class ClientAuthController {
    private final ClientAuthService clientAuthService;

    public ClientAuthController(ClientAuthService clientAuthService) {
        this.clientAuthService = clientAuthService;
    }

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse<ClientDTO>> signup(@RequestBody ClientRegistrationRequest courierRegistrationRequest) {
        AuthResponse<ClientDTO> response = clientAuthService.signUp(courierRegistrationRequest);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse<ClientDTO>> login(@RequestBody LoginRequest loginRequest) {
        AuthResponse<ClientDTO> response = clientAuthService.login(loginRequest);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/update")
    public ResponseEntity<AuthResponse<ClientDTO>> update(@RequestBody UpdateClientRequest courierDTO){
        AuthResponse<ClientDTO> response = clientAuthService.update(courierDTO);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/updatePassword")
    public ResponseEntity<AuthResponse<ClientDTO>> updatePassword(@RequestBody UpdatePasswordRequest request) {
        AuthResponse<ClientDTO> response = clientAuthService.updatePassword(request);

        return ResponseEntity.ok(response);
    }

}
