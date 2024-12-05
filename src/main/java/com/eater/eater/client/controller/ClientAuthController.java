package com.eater.eater.client.controller;

import com.eater.eater.DTO.auth.AuthResponse;
import com.eater.eater.DTO.auth.LoginRequest;
import com.eater.eater.DTO.auth.UpdatePasswordRequest;
import com.eater.eater.client.dto.ClientDTO;
import com.eater.eater.client.dto.ClientRegistrationRequest;
import com.eater.eater.client.dto.UpdateClientRequest;
import com.eater.eater.client.service.auth.ClientAuthServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/auth/client")
@RestController
public class ClientAuthController {
    private final ClientAuthServiceImpl clientAuthServiceImpl;

    public ClientAuthController(ClientAuthServiceImpl clientAuthServiceImpl) {
        this.clientAuthServiceImpl = clientAuthServiceImpl;
    }

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse<ClientDTO>> signup(@ModelAttribute ClientRegistrationRequest courierRegistrationRequest) {
        AuthResponse<ClientDTO> response = clientAuthServiceImpl.signUp(courierRegistrationRequest);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse<ClientDTO>> login(@RequestBody LoginRequest loginRequest) {
        AuthResponse<ClientDTO> response = clientAuthServiceImpl.login(loginRequest);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/update")
    public ResponseEntity<AuthResponse<ClientDTO>> update(@ModelAttribute UpdateClientRequest courierDTO){
        AuthResponse<ClientDTO> response = clientAuthServiceImpl.update(courierDTO);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/updatePassword")
    public ResponseEntity<AuthResponse<ClientDTO>> updatePassword(@RequestBody UpdatePasswordRequest request) {
        AuthResponse<ClientDTO> response = clientAuthServiceImpl.updatePassword(request);

        return ResponseEntity.ok(response);
    }

}
