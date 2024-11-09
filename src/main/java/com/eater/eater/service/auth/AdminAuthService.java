package com.eater.eater.service.auth;

import com.eater.eater.dto.admin.AdminDTO;
import com.eater.eater.dto.admin.UpdateAdminRequest;
import com.eater.eater.dto.auth.*;
import com.eater.eater.enums.Role;
import com.eater.eater.model.admin.Admin;
import com.eater.eater.repository.admin.AdminRepository;
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

    @Autowired
    public AdminAuthService(AdminRepository adminRepository, PasswordEncoder passwordEncoder, UserValidationService userValidationService, AuthUtilityService authUtilityService) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
        this.userValidationService = userValidationService;
        this.authUtilityService = authUtilityService;
    }

    public AuthResponse<AdminDTO> signUp(AdminRegistrationRequest input) {
        System.out.println(1);
        // validation
        userValidationService.signUpValidation(input.getPhone(), input.getEmail(), null, input.getPassword(), Role.ADMIN);
        System.out.println(1);

        // save in db
        Admin user = AdminMapper.authToEntity(input, passwordEncoder);
        adminRepository.save(user);
        System.out.println(1);

        // add in context
        authUtilityService.addContext(input.getEmail(), input.getPassword());
        System.out.println(1);

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
        userValidationService.updateValidation(request.getPhone(), request.getEmail(), currentUser.getEmail(), Role.ADMIN, request.getPassword(), currentUser.getPassword(), passwordEncoder);


        // update in db
        Admin userEntity = AdminMapper.updateRequestToEntity(request, currentUser);
        Admin updatedUser = adminRepository.save(userEntity);

        // add in context
        authUtilityService.addContext(updatedUser.getEmail(), request.getPassword());

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
        userValidationService.updatePasswordValidation(request.getOldPassword(), currentUser.getPassword(), passwordEncoder);

        // add in context
        authUtilityService.addContext(currentUser.getEmail(), request.getOldPassword());

        // update in db
        currentUser.setPassword(passwordEncoder.encode(request.getPassword()));
        Admin updatedUser = adminRepository.save(currentUser);

        // create and return response
        AdminDTO userDTO = AdminMapper.toDTO(updatedUser);
        return authUtilityService.createAuthResponse(updatedUser, userDTO);
    }

}
