package com.eater.eater.restaurantOwner.service.auth;

import com.eater.eater.DTO.auth.AuthResponse;
import com.eater.eater.DTO.auth.LoginRequest;
import com.eater.eater.DTO.auth.UpdatePasswordRequest;
import com.eater.eater.restaurantOwner.dto.RestaurantOwnerDTO;
import com.eater.eater.restaurantOwner.dto.RestaurantOwnerRegistrationRequest;
import com.eater.eater.restaurantOwner.dto.UpdateRestaurantOwnerRequest;

public interface RestaurantOwnerAuthService {
    AuthResponse<RestaurantOwnerDTO> signUp(RestaurantOwnerRegistrationRequest input);

    AuthResponse<RestaurantOwnerDTO> login(LoginRequest input);

    AuthResponse<RestaurantOwnerDTO> update(UpdateRestaurantOwnerRequest request);

    AuthResponse<RestaurantOwnerDTO> updatePassword(UpdatePasswordRequest request);
}
