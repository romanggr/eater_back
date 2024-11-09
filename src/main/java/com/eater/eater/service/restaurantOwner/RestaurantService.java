package com.eater.eater.service.restaurantOwner;

import com.eater.eater.dto.restaurantOwner.RestaurantDTO;
import com.eater.eater.dto.restaurantOwner.RestaurantDishDTO;
import com.eater.eater.dto.restaurantOwner.RestaurantDishRequest;
import com.eater.eater.dto.restaurantOwner.RestaurantDishUpdateRequest;
import com.eater.eater.dto.restaurantOwner.UpdateRestaurantRequest;

import java.util.List;

public interface RestaurantService {

    RestaurantDTO updateRestaurant(UpdateRestaurantRequest request);

    RestaurantDishDTO createDish(RestaurantDishRequest request);

    long deleteDish(Long id);

    RestaurantDishDTO updateDish(RestaurantDishUpdateRequest request);

    List<RestaurantDishDTO> getDishes();
}
