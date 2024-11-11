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
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final RestaurantDishRepository restaurantDishRepository;


    public RestaurantServiceImpl(RestaurantRepository restaurantRepository, RestaurantDishRepository restaurantDishRepository) {
        this.restaurantRepository = restaurantRepository;
        this.restaurantDishRepository = restaurantDishRepository;
    }


    public RestaurantDTO updateRestaurant(UpdateRestaurantRequest request) {
        RestaurantOwner currentUser = SecurityUtil.getCurrentUser(RestaurantOwner.class);
        Long restaurantId = currentUser.getRestaurant().getId();

        Restaurant currentRestaurant = restaurantRepository.findById(restaurantId).orElseThrow(
                () -> new EntityNotFoundException("Restaurant not found"));

        Restaurant restaurant = restaurantRepository.save(RestaurantMapper.updateRequestToEntity(request, currentRestaurant));

        return RestaurantMapper.toDTO(restaurant);
    }

    public RestaurantDishDTO createDish(RestaurantDishRequest request) {
        RestaurantOwner currentUser = SecurityUtil.getCurrentUser(RestaurantOwner.class);
        Restaurant restaurant = restaurantRepository.findById(currentUser.getId()).orElseThrow(
                () -> new EntityNotFoundException("Restaurant not found"));
        RestaurantDish dish = restaurantDishRepository.save(RestaurantDishMapper.toEntity(request, restaurant));

        return RestaurantDishMapper.toDTO(dish);
    }


    public long deleteDish(Long id) {
        if (!restaurantDishRepository.existsById(id)) {
            throw new EntityNotFoundException("Dish with ID " + id + " does not exist.");
        }

        restaurantDishRepository.deleteById(id);
        return id;
    }

    public RestaurantDishDTO updateDish(RestaurantDishUpdateRequest request) {
        RestaurantOwner currentUser = SecurityUtil.getCurrentUser(RestaurantOwner.class);
        Restaurant restaurant = restaurantRepository.findById(currentUser.getRestaurant().getId()).orElseThrow(
                () -> new EntityNotFoundException("Restaurant not found"));

        RestaurantDish restaurantDish = restaurantDishRepository.findById(request.getId()).orElseThrow(
                () -> new EntityNotFoundException("Dish not found"));

        RestaurantDish updatedDish = restaurantDishRepository.save(RestaurantDishMapper.toEntityUpdateRequest(request, restaurant, restaurantDish));

        return RestaurantDishMapper.toDTO(updatedDish);
    }


    public List<RestaurantDishDTO> getDishes() {
        RestaurantOwner currentUser = SecurityUtil.getCurrentUser(RestaurantOwner.class);
        Restaurant restaurant = restaurantRepository.findById(currentUser.getRestaurant().getId()).orElseThrow(
                () -> new EntityNotFoundException("Restaurant not found"));

        List<RestaurantDish> dishes = restaurantDishRepository.findByRestaurantId(restaurant.getId());
        List<RestaurantDishDTO> dishesDTO = dishes.stream().map(RestaurantDishMapper::toDTO).toList();
        return  dishesDTO;
    }
}
