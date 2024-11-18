package com.eater.eater.service.auth;

import com.eater.eater.dto.admin.AdminDTO;
import com.eater.eater.dto.admin.UpdateAdminRequest;
import com.eater.eater.dto.auth.*;
import com.eater.eater.model.admin.Admin;
import com.eater.eater.repository.admin.AdminRepository;
import com.eater.eater.security.EmailConfirmation;
import com.eater.eater.security.SecurityUtil;
import com.eater.eater.utils.mapper.admin.AdminMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AdminAuthService {
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserValidationService userValidationService;
    private final AuthUtilityService authUtilityService;
    private final EmailConfirmation emailConfirmation;

    @Autowired
    public AdminAuthService(AdminRepository adminRepository, PasswordEncoder passwordEncoder, UserValidationService userValidationService, AuthUtilityService authUtilityService, EmailConfirmation emailConfirmation) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
        this.userValidationService = userValidationService;
        this.authUtilityService = authUtilityService;
        this.emailConfirmation = emailConfirmation;
    }

    public AuthResponse<AdminDTO> signUp(AdminRegistrationRequest input) {
        // validation
        userValidationService.signUpValidation(input.getPhone(), input.getEmail(), input.getPassword());
        emailConfirmation.verifyEmailCode(input.getEmail(), input.getEmailCode());

        // save in db
        Admin user = AdminMapper.authToEntity(input, passwordEncoder);
        adminRepository.save(user);

        // add in context
        authUtilityService.addContext(input.getEmail(), input.getPassword());

        // create and return response
        AdminDTO userDTO = AdminMapper.toDTO(user);
        return authUtilityService.createAuthResponse(user, userDTO);
    }

    public AuthResponse<AdminDTO> login(LoginRequest input) {
        userValidationService.validateEmail(input.getEmail());
        // add in context
        authUtilityService.addContext(input.getEmail(), input.getPassword());

        // create and return response
        Admin user = adminRepository.findByEmail(input.getEmail()).
                orElseThrow(() -> new EntityNotFoundException("Admin not found"));
        AdminDTO userDTO = AdminMapper.toDTO(user);
        return authUtilityService.createAuthResponse(user, userDTO);
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
        authUtilityService.addContext(updatedUser.getEmail(), request.getCurrentPassword());

        // create and return response
        AdminDTO userDTO = AdminMapper.toDTO(updatedUser);
        return authUtilityService.createAuthResponse(updatedUser, userDTO);
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
        authUtilityService.addContext(currentUser.getEmail(), request.getCurrentPassword());

        // update in db
        currentUser.setPassword(passwordEncoder.encode(request.getNewPassword()));
        Admin updatedUser = adminRepository.save(currentUser);

        // create and return response
        AdminDTO userDTO = AdminMapper.toDTO(updatedUser);
        return authUtilityService.createAuthResponse(updatedUser, userDTO);
    }

}
