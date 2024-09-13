package com.eater.eater.service.restaurantOwner;

import com.eater.eater.dto.restaurantOwner.*;
import com.eater.eater.model.restaurantOwner.Restaurant;
import com.eater.eater.model.restaurantOwner.RestaurantDish;
import com.eater.eater.model.restaurantOwner.RestaurantOwner;
import com.eater.eater.repository.restaurantOwner.RestaurantDishRepository;
import com.eater.eater.repository.restaurantOwner.RestaurantRepository;
import com.eater.eater.security.SecurityUtil;
import com.eater.eater.utils.mapper.restaurantOwner.RestaurantDishMapper;
import com.eater.eater.utils.mapper.restaurantOwner.RestaurantMapper;
import com.eater.eater.utils.mapper.restaurantOwner.RestaurantOwnerMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final RestaurantMapper restaurantMapper;
    private final RestaurantDishRepository restaurantDishRepository;
    private final RestaurantDishMapper restaurantDishMapper;

    public RestaurantService(RestaurantRepository restaurantRepository, RestaurantMapper restaurantMapper, RestaurantDishRepository restaurantDishRepository, RestaurantDishMapper restaurantDishMapper) {
        this.restaurantRepository = restaurantRepository;
        this.restaurantMapper = restaurantMapper;
        this.restaurantDishRepository = restaurantDishRepository;
        this.restaurantDishMapper = restaurantDishMapper;
    }



    //update restaurant
    public RestaurantDTO updateRestaurant(UpdateRestaurantRequest request) {
        RestaurantOwner currentUser = SecurityUtil.getCurrentUser(RestaurantOwner.class);
        Long restaurantId = currentUser.getRestaurant().getId();

        Restaurant currentRestaurant = restaurantRepository.findById(restaurantId).orElseThrow(
                () -> new EntityNotFoundException("Restaurant not found"));

        Restaurant restaurant = restaurantRepository.save(restaurantMapper.updateRequestToEntity(request, currentRestaurant));

        return restaurantMapper.toDTO(restaurant);
    }

    //create dish
    public RestaurantDishDTO createDish(RestaurantDishRequest request) {
        RestaurantOwner currentUser = SecurityUtil.getCurrentUser(RestaurantOwner.class);
        Restaurant restaurant = restaurantRepository.findById(currentUser.getId()).orElseThrow(
                () -> new EntityNotFoundException("Restaurant not found"));
        RestaurantDish dish = restaurantDishRepository.save(restaurantDishMapper.toEntity(request, restaurant));

        return restaurantDishMapper.toDTO(dish);
    }

    //delete dish
    public long deleteDish(Long id) {
        if (!restaurantDishRepository.existsById(id)) {
            throw new EntityNotFoundException("Dish with ID " + id + " does not exist.");
        }

        restaurantDishRepository.deleteById(id);
        return id;
    }

    //update dish
    public RestaurantDishDTO updateDish(RestaurantDishUpdateRequest request) {
        RestaurantOwner currentUser = SecurityUtil.getCurrentUser(RestaurantOwner.class);
        Restaurant restaurant = restaurantRepository.findById(currentUser.getId()).orElseThrow(
                () -> new EntityNotFoundException("Restaurant not found"));

        RestaurantDish restaurantDish = restaurantDishRepository.findById(request.getId()).orElseThrow(
                () -> new EntityNotFoundException("Dish not found"));

        RestaurantDish updatedDish = restaurantDishRepository.save(restaurantDishMapper.toEntityUpdateRequest(request, restaurant, restaurantDish));

        return restaurantDishMapper.toDTO(updatedDish);
    }
}
