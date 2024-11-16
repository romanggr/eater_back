package com.eater.eater.service.restaurantOwner;

import com.eater.eater.dto.restaurantOwner.CreateRestaurantRequest;
import com.eater.eater.dto.restaurantOwner.RestaurantDTO;
import com.eater.eater.dto.restaurantOwner.RestaurantOwnerDTO;

public interface RestaurantOwnerService {

    RestaurantOwnerDTO getRestaurantOwner();

    RestaurantDTO createRestaurant(CreateRestaurantRequest request);
}

