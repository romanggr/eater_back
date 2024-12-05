package com.eater.eater.restaurantOwner.service.auth;

import com.eater.eater.email.service.EmailConfirmation;
import com.eater.eater.restaurantOwner.dto.RestaurantOwnerDTO;
import com.eater.eater.restaurantOwner.dto.UpdateRestaurantOwnerRequest;
import com.eater.eater.DTO.auth.AuthResponse;
import com.eater.eater.DTO.auth.LoginRequest;
import com.eater.eater.DTO.auth.UpdatePasswordRequest;
import com.eater.eater.restaurantOwner.dto.RestaurantOwnerRegistrationRequest;
import com.eater.eater.restaurantOwner.model.RestaurantOwner;
import com.eater.eater.restaurantOwner.repository.RestaurantOwnerRepository;
import com.eater.eater.restaurantOwner.utils.RestaurantOwnerMapper;
import com.eater.eater.security.SecurityUtil;
import com.eater.eater.security.UserValidationService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class RestaurantOwnerAuthServiceImpl implements RestaurantOwnerAuthService {
    private final RestaurantOwnerRepository restaurantOwnerRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserValidationService userValidationService;
    private final EmailConfirmation emailConfirmation;
    private final SecurityUtil securityUtil;



    public AuthResponse<RestaurantOwnerDTO> signUp(RestaurantOwnerRegistrationRequest input) {
        // validation
        userValidationService.signUpValidation(input.getPhone(), input.getEmail(), input.getPassword());
        emailConfirmation.verifyEmailCode(input.getEmail(), input.getEmailCode());

        // save in db
        RestaurantOwner user = RestaurantOwnerMapper.authToEntity(input, passwordEncoder);
        restaurantOwnerRepository.save(user);

        // add in context
        securityUtil.addContext(input.getEmail(), input.getPassword());

        // create and return response
        RestaurantOwnerDTO userDTO = RestaurantOwnerMapper.toDTO(user);
        return securityUtil.createAuthResponse(user, userDTO);
    }


    public AuthResponse<RestaurantOwnerDTO> login(LoginRequest input) {
        userValidationService.validateEmail(input.getEmail());
        // add in context
        securityUtil.addContext(input.getEmail(), input.getPassword());

        // create and return response
        RestaurantOwner user = restaurantOwnerRepository.findByEmail(input.getEmail()).
                orElseThrow(() -> new EntityNotFoundException("Restaurant Owner not found"));
        RestaurantOwnerDTO userDTO = RestaurantOwnerMapper.toDTO(user);
        return securityUtil.createAuthResponse(user, userDTO);
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
        securityUtil.addContext(updatedUser.getEmail(), request.getPassword());

        // create and return response
        RestaurantOwnerDTO userDTO = RestaurantOwnerMapper.toDTO(updatedUser);
        return securityUtil.createAuthResponse(updatedUser, userDTO);
    }


    public AuthResponse<RestaurantOwnerDTO> updatePassword(UpdatePasswordRequest request) {
        // get current data
        Long currentUserId = SecurityUtil.getCurrentUserId(RestaurantOwner.class);
        RestaurantOwner currentUser = restaurantOwnerRepository.findById(currentUserId)
                .orElseThrow(() -> new EntityNotFoundException("Restaurant Owner not found"));

        // data validation
        userValidationService.updatePasswordValidation(request.getNewPassword(), request.getCurrentPassword(), currentUser.getPassword(), passwordEncoder);

        // add in context
        securityUtil.addContext(currentUser.getEmail(), request.getCurrentPassword());

        // update in db
        currentUser.setPassword(passwordEncoder.encode(request.getNewPassword()));
        RestaurantOwner updatedUser = restaurantOwnerRepository.save(currentUser);

        // create and return response
        RestaurantOwnerDTO userDTO = RestaurantOwnerMapper.toDTO(updatedUser);
        return securityUtil.createAuthResponse(updatedUser, userDTO);
    }

}
