package com.eater.eater.service.restaurantOwner;

import com.eater.eater.dto.restaurantOwner.RestaurantDTO;
import com.eater.eater.dto.restaurantOwner.RestaurantOwnerDTO;
import com.eater.eater.exception.RestaurantAlreadyCreatedException;
import com.eater.eater.model.restaurantOwner.Restaurant;
import com.eater.eater.model.restaurantOwner.RestaurantOwner;
import com.eater.eater.repository.restaurantOwner.RestaurantOwnerRepository;
import com.eater.eater.repository.restaurantOwner.RestaurantRepository;
import com.eater.eater.security.SecurityUtil;
import com.eater.eater.utils.mapper.restaurantOwner.RestaurantMapper;
import com.eater.eater.utils.mapper.restaurantOwner.RestaurantOwnerMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class RestaurantOwnerService {
    private final RestaurantOwnerRepository restaurantOwnerRepository;
    private final RestaurantRepository restaurantRepository;


    public RestaurantOwnerService(RestaurantOwnerRepository restaurantOwnerRepository, RestaurantRepository restaurantRepository) {
        this.restaurantOwnerRepository = restaurantOwnerRepository;
        this.restaurantRepository = restaurantRepository;
    }

    //get restaurant owner
    public RestaurantOwnerDTO getRestaurantOwner() {
        RestaurantOwner currentUser = SecurityUtil.getCurrentUser(RestaurantOwner.class);
        RestaurantOwner user = restaurantOwnerRepository.findById(currentUser.getId()).orElseThrow(
                () -> new EntityNotFoundException("Restaurant Owner not found"));

        SecurityUtil.validateUserIsBanned(user.getRestaurantOwnerStatus());

        return RestaurantOwnerMapper.toDTO(user);
    }


    // create restaurant
    public RestaurantDTO createRestaurant(RestaurantDTO request) {
        RestaurantOwner currentUser = SecurityUtil.getCurrentUser(RestaurantOwner.class);
        System.out.println(request.getAvatarUrl());
        if (currentUser.getRestaurant() != null)
            throw new RestaurantAlreadyCreatedException("You can be the owner of only 1 restaurant");
        RestaurantOwner restaurantOwner = restaurantOwnerRepository.findById(currentUser.getId()).orElseThrow(
                () -> new EntityNotFoundException("Restaurant Owner not found"));

        Restaurant restaurant = restaurantRepository.save(RestaurantMapper.toEntity(request, restaurantOwner));
        System.out.println(restaurant.getAvatarUrl());
        return RestaurantMapper.toDTO(restaurant);
    }


}
