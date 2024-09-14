package com.eater.eater.utils.mapper.restaurantOwner;

import com.eater.eater.dto.restaurantOwner.RestaurantDishDTO;
import com.eater.eater.dto.restaurantOwner.RestaurantDishRequest;
import com.eater.eater.dto.restaurantOwner.RestaurantDishUpdateRequest;
import com.eater.eater.model.restaurantOwner.Restaurant;
import com.eater.eater.model.restaurantOwner.RestaurantDish;
import org.springframework.stereotype.Component;

public class RestaurantDishMapper {

    public static RestaurantDish toEntity(RestaurantDishRequest request, Restaurant restaurant) {
        if (request == null) return null;

        RestaurantDish restaurantDish = new RestaurantDish();
        restaurantDish.setName(request.getName());
        restaurantDish.setDescription(request.getDescription());
        restaurantDish.setPrice(request.getPrice());
        restaurantDish.setWeight(request.getWeight());
        restaurantDish.setImageUrl(request.getImageUrl());
        restaurantDish.setRestaurant(restaurant);

        return restaurantDish;
    }

    public static RestaurantDish toEntityUpdateRequest(RestaurantDishUpdateRequest request, Restaurant restaurant, RestaurantDish restaurantDish) {
        if (request == null) return null;

        restaurantDish.setId(restaurantDish.getId());
        restaurantDish.setName(request.getName());
        restaurantDish.setDescription(request.getDescription());
        restaurantDish.setPrice(request.getPrice());
        restaurantDish.setWeight(request.getWeight());
        restaurantDish.setImageUrl(request.getImageUrl());
        restaurantDish.setRestaurant(restaurant);

        return restaurantDish;
    }

    public static RestaurantDishDTO toDTO(RestaurantDish dish) {
        if (dish == null) return null;

        RestaurantDishDTO dishDTO = new RestaurantDishDTO();
        dishDTO.setId(dish.getId());
        dishDTO.setName(dish.getName());
        dishDTO.setDescription(dish.getDescription());
        dishDTO.setPrice(dish.getPrice());
        dishDTO.setWeight(dish.getWeight());
        dishDTO.setImageUrl(dish.getImageUrl());


        return dishDTO;
    }
}
