package com.eater.eater.client.service.auth;

import com.eater.eater.DTO.auth.AuthResponse;
import com.eater.eater.DTO.auth.LoginRequest;
import com.eater.eater.DTO.auth.UpdatePasswordRequest;
import com.eater.eater.client.dto.ClientDTO;
import com.eater.eater.client.dto.ClientRegistrationRequest;
import com.eater.eater.client.dto.UpdateClientRequest;

public interface ClientAuthService {
    AuthResponse<ClientDTO> signUp(ClientRegistrationRequest input);
    AuthResponse<ClientDTO> login(LoginRequest input);
    AuthResponse<ClientDTO> update(UpdateClientRequest request);
    AuthResponse<ClientDTO> updatePassword(UpdatePasswordRequest request);
}
