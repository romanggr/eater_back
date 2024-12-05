package com.eater.eater.courier.service.auth;

import com.eater.eater.DTO.auth.AuthResponse;
import com.eater.eater.DTO.auth.LoginRequest;
import com.eater.eater.DTO.auth.UpdatePasswordRequest;
import com.eater.eater.courier.dto.CourierDTO;
import com.eater.eater.courier.dto.auth.CourierRegistrationRequest;
import com.eater.eater.courier.dto.auth.UpdateCourierRequest;

public interface CourierAuthService {
    AuthResponse<CourierDTO> signUp(CourierRegistrationRequest input);

    AuthResponse<CourierDTO> login(LoginRequest input);

    AuthResponse<CourierDTO> update(UpdateCourierRequest request);

    AuthResponse<CourierDTO> updatePassword(UpdatePasswordRequest request);
}
