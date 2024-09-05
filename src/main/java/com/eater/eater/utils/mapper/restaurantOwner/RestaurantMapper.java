package com.eater.eater.utils.mapper.restaurantOwner;

import com.eater.eater.dto.restaurantOwner.RestaurantDTO;
import com.eater.eater.dto.restaurantOwner.UpdateRestaurantRequest;
import com.eater.eater.model.restaurantOwner.Restaurant;
import org.springframework.stereotype.Component;

@Component
public class RestaurantMapper {
    public Restaurant updateRequestToEntity(UpdateRestaurantRequest request, Restaurant restaurant) {
        if (request == null || restaurant == null) return null;

        restaurant.setAddress(request.getAddress());
        restaurant.setName(request.getName());
        restaurant.setDescription(request.getDescription());
        restaurant.setOpenFrom(request.isOpenFrom());
        restaurant.setOpenTo(request.isOpenTo());
        restaurant.setLatitude(request.getLatitude());
        restaurant.setLongitude(request.getLongitude());

        return restaurant;
    }

    public RestaurantDTO toDTO(Restaurant restaurant) {
        if (restaurant == null) return null;

        RestaurantDTO restaurantDTO = new RestaurantDTO();
        restaurantDTO.setId(restaurant.getId());
        restaurantDTO.setName(restaurant.getName());
        restaurantDTO.setDescription(restaurant.getDescription());
        restaurantDTO.setAddress(restaurant.getAddress());
        restaurantDTO.setLatitude(restaurant.getLatitude());
        restaurantDTO.setLongitude(restaurant.getLongitude());
        restaurantDTO.setOpenFrom(restaurant.isOpenFrom());
        restaurantDTO.setOpenTo(restaurant.isOpenTo());
        restaurantDTO.setRestaurantMenu(restaurant.getRestaurantDishes());

        return restaurantDTO;
    }
}
