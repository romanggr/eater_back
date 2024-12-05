package com.eater.eater.admin.service.auth;

import com.eater.eater.DTO.auth.AuthResponse;
import com.eater.eater.DTO.auth.LoginRequest;
import com.eater.eater.DTO.auth.UpdatePasswordRequest;
import com.eater.eater.admin.dto.AdminDTO;
import com.eater.eater.admin.dto.auth.AdminRegistrationRequest;
import com.eater.eater.admin.dto.auth.UpdateAdminRequest;

public interface AdminAuthService {
    AuthResponse<AdminDTO> signUp(AdminRegistrationRequest input);

    AuthResponse<AdminDTO> login(LoginRequest input);

    AuthResponse<AdminDTO> update(UpdateAdminRequest request);

    AuthResponse<AdminDTO> updatePassword(UpdatePasswordRequest request);

}
