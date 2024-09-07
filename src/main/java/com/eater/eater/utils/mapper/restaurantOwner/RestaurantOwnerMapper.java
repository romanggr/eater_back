package com.eater.eater.utils.mapper.restaurantOwner;

import com.eater.eater.dto.restaurantOwner.RestaurantDTO;
import com.eater.eater.dto.restaurantOwner.RestaurantOwnerDTO;
import com.eater.eater.dto.restaurantOwner.UpdateRestaurantOwnerRequest;
import com.eater.eater.dto.restaurantOwner.UpdateRestaurantRequest;
import com.eater.eater.model.restaurantOwner.Restaurant;
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

    public RestaurantDTO restaurantToDTO(Restaurant restaurant) {
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

        return restaurantDTO;
    }
    public Restaurant restaurantToEntity(RestaurantDTO restaurantDTO,RestaurantOwner restaurantOwner) {
        if (restaurantDTO == null) return null;

        Restaurant restaurant = new Restaurant();
        restaurant.setName(restaurantDTO.getName());
        restaurant.setDescription(restaurantDTO.getDescription());
        restaurant.setIsOpenFrom(restaurantDTO.getIsOpenFrom());
        restaurant.setIsOpenTo(restaurantDTO.getIsOpenTo());
        restaurant.setLatitude(restaurantDTO.getLatitude());
        restaurant.setLongitude(restaurantDTO.getLongitude());
        restaurant.setAddress(restaurantDTO.getAddress());
        restaurant.setRestaurantOwner(restaurantOwner);

        return restaurant;
    }

}
