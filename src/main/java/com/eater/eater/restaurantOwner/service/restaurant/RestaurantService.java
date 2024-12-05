package com.eater.eater.restaurantOwner.service.restaurant;

import com.eater.eater.restaurantOwner.dto.RestaurantDishDTO;
import com.eater.eater.restaurantOwner.dto.RestaurantDishRequest;
import com.eater.eater.restaurantOwner.dto.RestaurantDishUpdateRequest;

import java.util.List;

public interface RestaurantService {

    RestaurantDishDTO createDish(RestaurantDishRequest request);

    long deleteDish(Long id);

    RestaurantDishDTO updateDish(RestaurantDishUpdateRequest request);

    List<RestaurantDishDTO> getDishes();


}
