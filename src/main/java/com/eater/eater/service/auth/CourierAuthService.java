package com.eater.eater.service.auth;

import com.eater.eater.dto.auth.AuthResponse;
import com.eater.eater.dto.auth.CourierRegistrationRequest;
import com.eater.eater.dto.auth.LoginRequest;
import com.eater.eater.dto.auth.UpdatePasswordRequest;
import com.eater.eater.dto.courier.CourierDTO;
import com.eater.eater.dto.courier.UpdateCourierRequest;
import com.eater.eater.enums.FileCategory;
import com.eater.eater.enums.Role;
import com.eater.eater.model.courier.Courier;
import com.eater.eater.repository.courier.CourierRepository;
import com.eater.eater.security.SecurityUtil;
import com.eater.eater.service.S3.S3AvatarService;
import com.eater.eater.utils.mapper.courier.CourierMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class CourierAuthService {
    private final CourierRepository courierRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserValidationService userValidationService;
    private final AuthUtilityService authUtilityService;
    private final S3AvatarService s3AvatarService;

    @Autowired
    public CourierAuthService(CourierRepository courierRepository, PasswordEncoder passwordEncoder, UserValidationService userValidationService, AuthUtilityService authUtilityService, S3AvatarService s3AvatarService) {
        this.courierRepository = courierRepository;
        this.passwordEncoder = passwordEncoder;
        this.userValidationService = userValidationService;
        this.authUtilityService = authUtilityService;
        this.s3AvatarService = s3AvatarService;
    }

    public AuthResponse<CourierDTO> signUp(CourierRegistrationRequest input) {
        // validation
        userValidationService.signUpValidation(input.getPhone(), input.getEmail(), input.getPassword(), Role.COURIER);

        // save in db
        Courier user = CourierMapper.authToEntity(
                input,
                passwordEncoder,
                "https://eater-bucket.s3.eu-north-1.amazonaws.com/avatars/default-avatar.webp"
        );
        courierRepository.save(user);

        // save avatar in aws
        if (input.getAvatar() != null && !input.getAvatar().isEmpty()) {
            String avatarUrl = s3AvatarService.putObjectIntoBucket(
                    user.getId(),
                    input.getAvatar(),
                    FileCategory.AVATAR
            );
            user.setAvatarUrl(avatarUrl);
            courierRepository.save(user);
        }

        // add in context
        authUtilityService.addContext(input.getEmail(), input.getPassword());

        // create and return response
        CourierDTO userDTO = CourierMapper.toDTO(user);
        return authUtilityService.createAuthResponse(user, userDTO);
    }


    public AuthResponse<CourierDTO> login(LoginRequest input) {
        userValidationService.validateEmail(input.getEmail());
        // add in context
        authUtilityService.addContext(input.getEmail(), input.getPassword());

        // create and return response
        Courier user = courierRepository.findByEmail(input.getEmail()).
                orElseThrow(() -> new EntityNotFoundException("Courier not found"));
        CourierDTO userDTO = CourierMapper.toDTO(user);
        return authUtilityService.createAuthResponse(user, userDTO);
    }

    public AuthResponse<CourierDTO> update(UpdateCourierRequest request) {
        // get current data
        Long currentUserId = SecurityUtil.getCurrentUserId(Courier.class);
        Courier currentUser = courierRepository.findById(currentUserId)
                .orElseThrow(() -> new EntityNotFoundException("Courier not found"));

        // data validation
        userValidationService.updateValidation(request.getPhone(), request.getEmail(), currentUser.getEmail(), Role.COURIER, request.getPassword(), currentUser.getPassword(), passwordEncoder);

        // update in aws
        if (request.getAvatar() != null && !request.getAvatar().isEmpty()) {
            String avatarUrl = s3AvatarService.putObjectIntoBucket(
                    currentUser.getId(),
                    request.getAvatar(),
                    FileCategory.AVATAR
            );
            currentUser.setAvatarUrl(avatarUrl);
        }

        // update in db
        Courier updatedUser = courierRepository.save(CourierMapper.updateRequestToEntity(request, currentUser));

        // add in context
        authUtilityService.addContext(updatedUser.getEmail(), request.getPassword());

        // create and return response
        CourierDTO userDTO = CourierMapper.toDTO(updatedUser);
        return authUtilityService.createAuthResponse(updatedUser, userDTO);
    }


    public AuthResponse<CourierDTO> updatePassword(UpdatePasswordRequest request) {
        // get current data
        Long currentUserId = SecurityUtil.getCurrentUserId(Courier.class);
        Courier currentUser = courierRepository.findById(currentUserId)
                .orElseThrow(() -> new EntityNotFoundException("Courier not found"));

        // data validation
        userValidationService.updatePasswordValidation(request.getNewPassword(), request.getCurrentPassword(), currentUser.getPassword(), passwordEncoder);

        // add in context
        authUtilityService.addContext(currentUser.getEmail(), request.getCurrentPassword());

        // update in db
        currentUser.setPassword(passwordEncoder.encode(request.getNewPassword()));
        Courier updatedUser = courierRepository.save(currentUser);

        // create and return response
        CourierDTO userDTO = CourierMapper.toDTO(updatedUser);
        return authUtilityService.createAuthResponse(updatedUser, userDTO);
    }


}
