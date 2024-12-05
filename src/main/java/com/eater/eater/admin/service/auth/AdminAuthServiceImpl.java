package com.eater.eater.admin.service.auth;

import com.eater.eater.DTO.auth.AuthResponse;
import com.eater.eater.DTO.auth.LoginRequest;
import com.eater.eater.DTO.auth.UpdatePasswordRequest;
import com.eater.eater.admin.dto.AdminDTO;
import com.eater.eater.admin.dto.auth.AdminRegistrationRequest;
import com.eater.eater.admin.dto.auth.UpdateAdminRequest;
import com.eater.eater.admin.model.Admin;
import com.eater.eater.admin.repository.AdminRepository;
import com.eater.eater.admin.utils.AdminMapper;
import com.eater.eater.email.service.EmailConfirmation;
import com.eater.eater.security.SecurityUtil;
import com.eater.eater.security.UserValidationService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AdminAuthServiceImpl implements AdminAuthService {
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserValidationService userValidationService;
    private final SecurityUtil securityUtil;
    private final EmailConfirmation emailConfirmation;

    public AuthResponse<AdminDTO> signUp(AdminRegistrationRequest input) {
        // validation
        userValidationService.signUpValidation(input.getPhone(), input.getEmail(), input.getPassword());
        emailConfirmation.verifyEmailCode(input.getEmail(), input.getEmailCode());

        // save in db
        Admin user = AdminMapper.authToEntity(input, passwordEncoder);
        adminRepository.save(user);

        // add in context
        securityUtil.addContext(input.getEmail(), input.getPassword());

        // create and return response
        AdminDTO userDTO = AdminMapper.toDTO(user);
        return securityUtil.createAuthResponse(user, userDTO);
    }

    public AuthResponse<AdminDTO> login(LoginRequest input) {
        userValidationService.validateEmail(input.getEmail());
        // add in context
        securityUtil.addContext(input.getEmail(), input.getPassword());

        // create and return response
        Admin user = adminRepository.findByEmail(input.getEmail()).
                orElseThrow(() -> new EntityNotFoundException("Admin not found"));
        AdminDTO userDTO = AdminMapper.toDTO(user);
        return securityUtil.createAuthResponse(user, userDTO);
    }

    public AuthResponse<AdminDTO> update(UpdateAdminRequest request) {
        // get current data
        Long currentUserId = SecurityUtil.getCurrentUserId(Admin.class);
        Admin currentUser = adminRepository.findById(currentUserId)
                .orElseThrow(() -> new EntityNotFoundException("Admin not found"));

        // data validation
        userValidationService.updateValidation(request.getPhone(),
                request.getEmail(), currentUser.getEmail(),
                request.getCurrentPassword(), currentUser.getPassword(), passwordEncoder);

        // update in db
        Admin userEntity = AdminMapper.updateRequestToEntity(request, currentUser);
        Admin updatedUser = adminRepository.save(userEntity);

        // add in context
        securityUtil.addContext(updatedUser.getEmail(), request.getCurrentPassword());

        // create and return response
        AdminDTO userDTO = AdminMapper.toDTO(updatedUser);
        return securityUtil.createAuthResponse(updatedUser, userDTO);
    }

    public AuthResponse<AdminDTO> updatePassword(UpdatePasswordRequest request) {
        // get current data
        Long currentUserId = SecurityUtil.getCurrentUserId(Admin.class);
        Admin currentUser = adminRepository.findById(currentUserId)
                .orElseThrow(() -> new EntityNotFoundException("Admin not found"));

        // data validation
        userValidationService.updatePasswordValidation(request.getNewPassword(),
                request.getCurrentPassword(), currentUser.getPassword(), passwordEncoder);

        // add in context
        securityUtil.addContext(currentUser.getEmail(), request.getCurrentPassword());

        // update in db
        currentUser.setPassword(passwordEncoder.encode(request.getNewPassword()));
        Admin updatedUser = adminRepository.save(currentUser);

        // create and return response
        AdminDTO userDTO = AdminMapper.toDTO(updatedUser);
        return securityUtil.createAuthResponse(updatedUser, userDTO);
    }

}
