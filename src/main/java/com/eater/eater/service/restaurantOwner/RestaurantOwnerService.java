package com.eater.eater.service.restaurantOwner;

import com.eater.eater.dto.auth.UpdatePasswordRequest;
import com.eater.eater.dto.restaurantOwner.RestaurantDTO;
import com.eater.eater.dto.restaurantOwner.RestaurantOwnerDTO;
import com.eater.eater.dto.restaurantOwner.UpdateRestaurantOwnerRequest;
import com.eater.eater.dto.restaurantOwner.UpdateRestaurantRequest;
import com.eater.eater.exception.RestaurantAlreadyCreatedException;
import com.eater.eater.model.restaurantOwner.Restaurant;
import com.eater.eater.model.restaurantOwner.RestaurantOwner;
import com.eater.eater.repository.restaurantOwner.RestaurantOwnerRepository;
import com.eater.eater.repository.restaurantOwner.RestaurantRepository;
import com.eater.eater.security.SecurityUtil;
import com.eater.eater.service.auth.UserValidationService;
import com.eater.eater.utils.mapper.restaurantOwner.RestaurantOwnerMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RestaurantOwnerService {
    private final RestaurantOwnerRepository restaurantOwnerRepository;
    private final RestaurantRepository restaurantRepository;
    private final RestaurantOwnerMapper restaurantOwnerMapper;
    private final UserValidationService userValidationService;
    private final PasswordEncoder passwordEncoder;

    public RestaurantOwnerService(RestaurantOwnerRepository restaurantOwnerRepository, RestaurantRepository restaurantRepository, RestaurantOwnerMapper restaurantOwnerMapper, UserValidationService userValidationService, PasswordEncoder passwordEncoder) {
        this.restaurantOwnerRepository = restaurantOwnerRepository;
        this.restaurantRepository = restaurantRepository;
        this.restaurantOwnerMapper = restaurantOwnerMapper;
        this.userValidationService = userValidationService;
        this.passwordEncoder = passwordEncoder;
    }

    //get restaurant owner
    public RestaurantOwnerDTO getRestaurantOwner() {
        RestaurantOwner currentUser = SecurityUtil.getCurrentUser(RestaurantOwner.class);
        RestaurantOwner user = restaurantOwnerRepository.findById(currentUser.getId()).orElseThrow(
                () -> new EntityNotFoundException("Restaurant Owner not found"));

        return restaurantOwnerMapper.toDTO(user);
    }

    //update restaurant owner
    public RestaurantOwnerDTO updateRestaurantOwner(UpdateRestaurantOwnerRequest request) {
        Long userId = SecurityUtil.getCurrentUserId(RestaurantOwner.class);

        RestaurantOwner currentRestaurantOwner = restaurantOwnerRepository.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("Restaurant Owner not found"));

        userValidationService.validateUser(request.getPhone(), currentRestaurantOwner.getPhone(), request.getEmail(), currentRestaurantOwner.getEmail());
        RestaurantOwner restaurantOwner = restaurantOwnerRepository.save(restaurantOwnerMapper.updateRequestToEntity(request, currentRestaurantOwner));

        return restaurantOwnerMapper.toDTO(restaurantOwner);
    }

    public RestaurantOwnerDTO updatePassword(UpdatePasswordRequest request) {
        Long currentUserId = SecurityUtil.getCurrentUserId(RestaurantOwner.class);
        RestaurantOwner user = restaurantOwnerRepository.findById(currentUserId).orElseThrow(
                () -> new EntityNotFoundException("Restaurant Owner not found"));

        userValidationService.validatePassword(request.getPassword());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        RestaurantOwner restaurantOwner = restaurantOwnerRepository.save(user);

        return restaurantOwnerMapper.toDTO(restaurantOwner);
    }

    public RestaurantDTO createRestaurant(RestaurantDTO request) {
        RestaurantOwner currentUser = SecurityUtil.getCurrentUser(RestaurantOwner.class);

        if (currentUser.getRestaurant() != null)
            throw new RestaurantAlreadyCreatedException("You can be the owner of only 1 restaurant");
        RestaurantOwner restaurantOwner = restaurantOwnerRepository.findById(currentUser.getId()).orElseThrow(
                () -> new EntityNotFoundException("Restaurant Owner not found"));

        Restaurant restaurant = restaurantRepository.save(restaurantOwnerMapper.restaurantToEntity(request, restaurantOwner));

        return restaurantOwnerMapper.restaurantToDTO(restaurant);
    }


}
