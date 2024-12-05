package com.eater.eater.restaurantOwner.service.owner;

import com.eater.eater.restaurantOwner.dto.CreateRestaurantRequest;
import com.eater.eater.restaurantOwner.dto.RestaurantDTO;
import com.eater.eater.restaurantOwner.dto.UpdateRestaurantRequest;

public interface RestaurantOwnerService {
    RestaurantDTO updateRestaurant(UpdateRestaurantRequest request);

    RestaurantDTO createRestaurant(CreateRestaurantRequest request);
}

