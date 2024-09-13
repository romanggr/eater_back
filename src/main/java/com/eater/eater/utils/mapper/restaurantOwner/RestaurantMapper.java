package com.eater.eater.utils.mapper.restaurantOwner;

import com.eater.eater.dto.restaurantOwner.RestaurantDTO;
import com.eater.eater.dto.restaurantOwner.UpdateRestaurantRequest;
import com.eater.eater.model.restaurantOwner.Restaurant;
import com.eater.eater.model.restaurantOwner.RestaurantOwner;
import org.springframework.stereotype.Component;

@Component
public class RestaurantMapper {

    public RestaurantDTO toDTO(Restaurant restaurant) {
        if (restaurant == null) return null;

        RestaurantDTO restaurantDTO = new RestaurantDTO();
        restaurantDTO.setId(restaurant.getId());
        restaurantDTO.setName(restaurant.getName());
        restaurantDTO.setDescription(restaurant.getDescription());
        restaurantDTO.setAddress(restaurant.getAddress());
        restaurantDTO.setLatitude(restaurant.getLatitude());
        restaurantDTO.setLongitude(restaurant.getLongitude());
        restaurantDTO.setIsOpenFrom(restaurant.getIsOpenFrom());
        restaurantDTO.setIsOpenTo(restaurant.getIsOpenTo());
        restaurantDTO.setRestaurantMenu(restaurant.getRestaurantDishes());
        restaurantDTO.setAvatarUrl(restaurant.getAvatarUrl());

        return restaurantDTO;
    }
    public Restaurant toEntity(RestaurantDTO restaurantDTO, RestaurantOwner restaurantOwner) {
        if (restaurantDTO == null) return null;

        Restaurant restaurant = new Restaurant();
        restaurant.setName(restaurantDTO.getName());
        restaurant.setDescription(restaurantDTO.getDescription());
        restaurant.setIsOpenFrom(restaurantDTO.getIsOpenFrom());
        restaurant.setIsOpenTo(restaurantDTO.getIsOpenTo());
        restaurant.setLatitude(restaurantDTO.getLatitude());
        restaurant.setLongitude(restaurantDTO.getLongitude());
        restaurant.setAddress(restaurantDTO.getAddress());
        restaurant.setAvatarUrl(restaurantDTO.getAvatarUrl());
        restaurant.setRestaurantOwner(restaurantOwner);

        return restaurant;
    }

    public Restaurant updateRequestToEntity(UpdateRestaurantRequest request, Restaurant restaurant) {
        if (request == null || restaurant == null) return null;

        restaurant.setAddress(request.getAddress());
        restaurant.setName(request.getName());
        restaurant.setDescription(request.getDescription());
        restaurant.setIsOpenFrom(request.getIsOpenFrom());
        restaurant.setIsOpenTo(request.getIsOpenTo());
        restaurant.setLatitude(request.getLatitude());
        restaurant.setLongitude(request.getLongitude());
        restaurant.setAvatarUrl(request.getAvatarUrl());

        return restaurant;
    }


}
