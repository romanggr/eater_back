package com.eater.eater.service.auth;

import com.eater.eater.dto.auth.*;
import com.eater.eater.dto.restaurantOwner.RestaurantOwnerDTO;
import com.eater.eater.dto.restaurantOwner.UpdateRestaurantOwnerRequest;
import com.eater.eater.model.restaurantOwner.RestaurantOwner;
import com.eater.eater.repository.restaurantOwner.RestaurantOwnerRepository;
import com.eater.eater.security.EmailConfirmation;
import com.eater.eater.security.SecurityUtil;
import com.eater.eater.utils.mapper.restaurantOwner.RestaurantOwnerMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class RestaurantOwnerAuthService {
    private final RestaurantOwnerRepository restaurantOwnerRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserValidationService userValidationService;
    private final AuthUtilityService authUtilityService;
    private final EmailConfirmation emailConfirmation;

    @Autowired
    public RestaurantOwnerAuthService(RestaurantOwnerRepository restaurantOwnerRepository, PasswordEncoder passwordEncoder, UserValidationService userValidationService, AuthUtilityService authUtilityService, EmailConfirmation emailConfirmation) {
        this.restaurantOwnerRepository = restaurantOwnerRepository;
        this.passwordEncoder = passwordEncoder;
        this.userValidationService = userValidationService;
        this.authUtilityService = authUtilityService;
        this.emailConfirmation = emailConfirmation;
    }

    public AuthResponse<RestaurantOwnerDTO> signUp(RestaurantOwnerRegistrationRequest input) {
        // validation
        userValidationService.signUpValidation(input.getPhone(), input.getEmail(), input.getPassword());
        emailConfirmation.verifyEmailCode(input.getEmail(), input.getEmailCode());

        // save in db
        RestaurantOwner user = RestaurantOwnerMapper.authToEntity(input, passwordEncoder);
        restaurantOwnerRepository.save(user);

        // add in context
        authUtilityService.addContext(input.getEmail(), input.getPassword());

        // create and return response
        RestaurantOwnerDTO userDTO = RestaurantOwnerMapper.toDTO(user);
        return authUtilityService.createAuthResponse(user, userDTO);
    }

    public AuthResponse<RestaurantOwnerDTO> login(LoginRequest input) {
        userValidationService.validateEmail(input.getEmail());
        // add in context
        authUtilityService.addContext(input.getEmail(), input.getPassword());

        // create and return response
        RestaurantOwner user = restaurantOwnerRepository.findByEmail(input.getEmail()).
                orElseThrow(() -> new EntityNotFoundException("Restaurant Owner not found"));
        RestaurantOwnerDTO userDTO = RestaurantOwnerMapper.toDTO(user);
        return authUtilityService.createAuthResponse(user, userDTO);
    }

    public AuthResponse<RestaurantOwnerDTO> update(UpdateRestaurantOwnerRequest request) {
        // get current data
        Long currentUserId = SecurityUtil.getCurrentUserId(RestaurantOwner.class);
        RestaurantOwner currentUser = restaurantOwnerRepository.findById(currentUserId)
                .orElseThrow(() -> new EntityNotFoundException("Restaurant Owner not found"));

        // data validation
        userValidationService.updateValidation(request.getPhone(), request.getEmail(), currentUser.getEmail(), request.getPassword(), currentUser.getPassword(), passwordEncoder);

        // update in db
        RestaurantOwner userEntity = RestaurantOwnerMapper.updateRequestToEntity(request, currentUser);
        RestaurantOwner updatedUser = restaurantOwnerRepository.save(userEntity);

        // add in context
        authUtilityService.addContext(updatedUser.getEmail(), request.getPassword());

        // create and return response
        RestaurantOwnerDTO userDTO = RestaurantOwnerMapper.toDTO(updatedUser);
        return authUtilityService.createAuthResponse(updatedUser, userDTO);
    }

    public AuthResponse<RestaurantOwnerDTO> updatePassword(UpdatePasswordRequest request) {
        // get current data
        Long currentUserId = SecurityUtil.getCurrentUserId(RestaurantOwner.class);
        RestaurantOwner currentUser = restaurantOwnerRepository.findById(currentUserId)
                .orElseThrow(() -> new EntityNotFoundException("Restaurant Owner not found"));

        // data validation
        userValidationService.updatePasswordValidation(request.getNewPassword(), request.getCurrentPassword(), currentUser.getPassword(), passwordEncoder);

        // add in context
        authUtilityService.addContext(currentUser.getEmail(), request.getCurrentPassword());

        // update in db
        currentUser.setPassword(passwordEncoder.encode(request.getNewPassword()));
        RestaurantOwner updatedUser = restaurantOwnerRepository.save(currentUser);

        // create and return response
        RestaurantOwnerDTO userDTO = RestaurantOwnerMapper.toDTO(updatedUser);
        return authUtilityService.createAuthResponse(updatedUser, userDTO);
    }

}
