package com.eater.eater.utils.mapper.restaurantOwner;

import com.eater.eater.dto.admin.RestaurantOwnersForAdminDTO;
import com.eater.eater.dto.restaurantOwner.RestaurantDTO;
import com.eater.eater.dto.restaurantOwner.RestaurantOwnerDTO;
import com.eater.eater.dto.restaurantOwner.UpdateRestaurantOwnerRequest;
import com.eater.eater.dto.restaurantOwner.UpdateRestaurantRequest;
import com.eater.eater.model.restaurantOwner.Restaurant;
import com.eater.eater.model.restaurantOwner.RestaurantOwner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RestaurantOwnerMapper {
    public RestaurantOwnerDTO toDTO(RestaurantOwner restaurantOwner) {
        RestaurantOwnerDTO ownerDTO = new RestaurantOwnerDTO();
        ownerDTO.setId(restaurantOwner.getId());
        ownerDTO.setEmail(restaurantOwner.getEmail());
        ownerDTO.setPhone(restaurantOwner.getPhone());
        ownerDTO.setName(restaurantOwner.getName());
        ownerDTO.setRestaurant(restaurantOwner.getRestaurant());

        return ownerDTO;
    }


    public List<RestaurantOwnersForAdminDTO> allRestaurantOwnerToDTO(List<RestaurantOwner> restaurantOwners) {
        if (restaurantOwners == null || restaurantOwners.isEmpty()) {
            return new ArrayList<>();
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
}
