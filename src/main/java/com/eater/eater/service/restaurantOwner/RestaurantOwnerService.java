package com.eater.eater.service.restaurantOwner;

import com.eater.eater.dto.restaurantOwner.RestaurantOwnerDTO;
import com.eater.eater.dto.restaurantOwner.UpdateRestaurantOwnerRequest;
import com.eater.eater.dto.restaurantOwner.UpdateRestaurantRequest;
import com.eater.eater.model.restaurantOwner.RestaurantOwner;
import com.eater.eater.repository.restaurantOwner.RestaurantOwnerRepository;
import com.eater.eater.security.SecurityUtil;
import com.eater.eater.service.auth.UserValidationService;
import com.eater.eater.utils.mapper.restaurantOwner.RestaurantOwnerMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class RestaurantOwnerService {
//    private final RestaurantOwnerRepository restaurantOwnerRepository;
//    private final RestaurantOwnerMapper restaurantOwnerMapper;
//    private final UserValidationService userValidationService;
//
//    public RestaurantOwnerService(RestaurantOwnerRepository restaurantOwnerRepository, RestaurantOwnerMapper restaurantOwnerMapper, UserValidationService userValidationService) {
//        this.restaurantOwnerRepository = restaurantOwnerRepository;
//        this.restaurantOwnerMapper = restaurantOwnerMapper;
//        this.userValidationService = userValidationService;
//    }
//
//    //get restaurant owner
//    public RestaurantOwnerDTO getRestaurantOwner() {
//        RestaurantOwner currentUser = SecurityUtil.getCurrentUser(RestaurantOwner.class);
//
//        return restaurantOwnerMapper.toDTO(currentUser);
//    }
//
//    //update restaurant owner
//    public RestaurantOwnerDTO updateRestaurantOwner(UpdateRestaurantOwnerRequest request){
//        Long userId = SecurityUtil.getCurrentUserId(RestaurantOwner.class);
//
//        RestaurantOwner currentRestaurantOwner = restaurantOwnerRepository.findById(userId).orElseThrow(
//                () -> new EntityNotFoundException("Restaurant Owner not found"));
//
//        userValidationService.validateUser(request.getEmail(), request.getPhone());
//        RestaurantOwner restaurantOwner = restaurantOwnerRepository.save(restaurantOwnerMapper.updateRequestToEntity(request, currentRestaurantOwner));
//
//
//        return restaurantOwnerMapper.toDTO(restaurantOwner);
//    }


}
