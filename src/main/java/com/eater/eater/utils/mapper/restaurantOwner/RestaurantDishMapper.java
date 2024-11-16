package com.eater.eater.utils.mapper.restaurantOwner;

import com.eater.eater.dto.restaurantOwner.RestaurantDishDTO;
import com.eater.eater.dto.restaurantOwner.RestaurantDishRequest;
import com.eater.eater.dto.restaurantOwner.RestaurantDishUpdateRequest;
import com.eater.eater.model.restaurantOwner.Restaurant;
import com.eater.eater.model.restaurantOwner.RestaurantDish;

public class RestaurantDishMapper {

    public static RestaurantDish toEntity(RestaurantDishRequest request, Restaurant restaurant, String defaultUrl) {
        if (request == null) {
            throw new IllegalArgumentException("Parameter cannot be null or empty.");
        }

        RestaurantDish restaurantDish = new RestaurantDish();
        restaurantDish.setName(request.getName());
        restaurantDish.setDescription(request.getDescription());
        restaurantDish.setPrice(request.getPrice());
        restaurantDish.setWeight(request.getWeight());
        restaurantDish.setImageUrl(defaultUrl);
        restaurantDish.setRestaurant(restaurant);

        return restaurantDish;
    }

    public static RestaurantDish toEntityUpdateRequest(RestaurantDishUpdateRequest request, RestaurantDish restaurantDish) {
        if (request == null) {
            throw new IllegalArgumentException("Parameter cannot be null or empty.");
        }

        restaurantDish.setName(request.getName());
        restaurantDish.setDescription(request.getDescription());
        restaurantDish.setPrice(request.getPrice());
        restaurantDish.setWeight(request.getWeight());

        return restaurantDish;
    }

    public static RestaurantDishDTO toDTO(RestaurantDish dish) {
        if (dish == null) {
            throw new IllegalArgumentException("Parameter cannot be null or empty.");
        }

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
