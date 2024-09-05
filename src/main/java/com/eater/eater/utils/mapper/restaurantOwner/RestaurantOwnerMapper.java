package com.eater.eater.utils.mapper.restaurantOwner;

import com.eater.eater.dto.restaurantOwner.RestaurantOwnerDTO;
import com.eater.eater.dto.restaurantOwner.UpdateRestaurantOwnerRequest;
import com.eater.eater.model.restaurantOwner.RestaurantOwner;
import org.springframework.stereotype.Component;

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

    public RestaurantOwner updateRequestToEntity(UpdateRestaurantOwnerRequest request, RestaurantOwner restaurantOwner) {
        if (request == null || restaurantOwner == null) return null;

        restaurantOwner.setName(request.getName());
        restaurantOwner.setPhone(request.getPhone());
        restaurantOwner.setEmail(request.getEmail());

        return restaurantOwner;
    }
}
