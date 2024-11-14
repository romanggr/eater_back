package com.eater.eater.utils.mapper.restaurantOwner;

import com.eater.eater.dto.admin.RestaurantOwnersForAdminDTO;
import com.eater.eater.dto.auth.RestaurantOwnerRegistrationRequest;
import com.eater.eater.dto.restaurantOwner.RestaurantOwnerDTO;
import com.eater.eater.dto.restaurantOwner.UpdateRestaurantOwnerRequest;
import com.eater.eater.model.restaurantOwner.RestaurantOwner;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.stream.Collectors;

public class RestaurantOwnerMapper {
    public static RestaurantOwnerDTO toDTO(RestaurantOwner restaurantOwner) {
        RestaurantOwnerDTO ownerDTO = new RestaurantOwnerDTO();
        ownerDTO.setId(restaurantOwner.getId());
        ownerDTO.setEmail(restaurantOwner.getEmail());
        ownerDTO.setPhone(restaurantOwner.getPhone());
        ownerDTO.setName(restaurantOwner.getName());
        ownerDTO.setRestaurant(restaurantOwner.getRestaurant());

        return ownerDTO;
    }


    public static List<RestaurantOwnersForAdminDTO> allRestaurantOwnerToDTO(List<RestaurantOwner> restaurantOwners) {
        if (restaurantOwners == null || restaurantOwners.isEmpty()) {
            throw new IllegalArgumentException("Parameter cannot be null or empty.");
        }

        return restaurantOwners.stream()
                .map(owner -> {
                    RestaurantOwnersForAdminDTO dto = new RestaurantOwnersForAdminDTO();
                    dto.setId(owner.getId());
                    dto.setName(owner.getName());
                    dto.setIsOpenFrom(owner.getRestaurant().getIsOpenFrom());
                    dto.setIsOpenTo(owner.getRestaurant().getIsOpenTo());
                    dto.setAvatarUrl(owner.getRestaurant().getAvatarUrl());
                    dto.setPhoneNumber(owner.getPhone());
                    dto.setRestaurantOwnerStatus(owner.getRestaurantOwnerStatus());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public static RestaurantOwner authToEntity(RestaurantOwnerRegistrationRequest input, PasswordEncoder passwordEncoder) {
        RestaurantOwner user = new RestaurantOwner();
        user.setName(input.getName());
        user.setEmail(input.getEmail());
        user.setPhone(input.getPhone());
        user.setPassword(passwordEncoder.encode(input.getPassword()));

        return user;
    }

    public static RestaurantOwner updateRequestToEntity(UpdateRestaurantOwnerRequest request, RestaurantOwner restaurantOwner) {
        if (request == null || restaurantOwner == null) {
            throw new IllegalArgumentException("Parameter cannot be null or empty.");
        }

        restaurantOwner.setName(request.getName());
        restaurantOwner.setPhone(request.getPhone());
        restaurantOwner.setEmail(request.getEmail());

        return restaurantOwner;
    }
}
