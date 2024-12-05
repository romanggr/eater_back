package com.eater.eater.restaurantOwner.service.owner;


import com.eater.eater.enums.FileCategory;
import com.eater.eater.exception.RestaurantAlreadyCreatedException;
import com.eater.eater.restaurantOwner.dto.CreateRestaurantRequest;
import com.eater.eater.restaurantOwner.dto.RestaurantDTO;
import com.eater.eater.restaurantOwner.dto.UpdateRestaurantRequest;
import com.eater.eater.restaurantOwner.model.Restaurant;
import com.eater.eater.restaurantOwner.model.RestaurantOwner;
import com.eater.eater.restaurantOwner.repository.RestaurantOwnerRepository;
import com.eater.eater.restaurantOwner.repository.RestaurantRepository;
import com.eater.eater.restaurantOwner.utils.RestaurantMapper;
import com.eater.eater.security.SecurityUtil;
import com.eater.eater.service.S3.S3AvatarService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestaurantOwnerServiceImpl implements RestaurantOwnerService {
    private final RestaurantOwnerRepository restaurantOwnerRepository;
    private final RestaurantRepository restaurantRepository;
    private final S3AvatarService s3AvatarService;


    public RestaurantDTO createRestaurant(CreateRestaurantRequest request) {
        RestaurantOwner currentUser = SecurityUtil.getCurrentUser(RestaurantOwner.class);
        RestaurantOwner restaurantOwner = restaurantOwnerRepository.findById(currentUser.getId())
                .orElseThrow(() -> new EntityNotFoundException("Restaurant Owner not found"));

        if (restaurantOwner.getRestaurant() != null) {
            throw new RestaurantAlreadyCreatedException("You can be the owner of only 1 restaurant");
        }

        Restaurant restaurant = RestaurantMapper.restaurantToEntity(
                request,
                restaurantOwner,
                "https://eater-bucket.s3.eu-north-1.amazonaws.com/avatars/default.jpg"
        );

        restaurant = restaurantRepository.save(restaurant);

        if (request.getImage() != null && !request.getImage().isEmpty()) {
            String avatarUrl = s3AvatarService.putObjectIntoBucket(
                    restaurant.getId(),
                    request.getImage(),
                    FileCategory.RESTAURANT_PHOTO
            );
            restaurant.setAvatarUrl(avatarUrl);
            restaurantRepository.save(restaurant);
        }

        return RestaurantMapper.toDTO(restaurant);
    }


    public RestaurantDTO updateRestaurant(UpdateRestaurantRequest request) {
        RestaurantOwner currentUser = SecurityUtil.getCurrentUser(RestaurantOwner.class);
        Restaurant restaurant = restaurantRepository.findRestaurantByRestaurantOwnerId(currentUser.getId())
                .orElseThrow(() -> new EntityNotFoundException("Restaurant not found"));

        if (request.getImage() != null && !request.getImage().isEmpty()) {
            String avatarUrl = s3AvatarService.putObjectIntoBucket(
                    restaurant.getId(),
                    request.getImage(),
                    FileCategory.RESTAURANT_PHOTO
            );
            restaurant.setAvatarUrl(avatarUrl);
        }

        RestaurantMapper.updateRequestToEntity(request, restaurant);
        restaurant = restaurantRepository.save(restaurant);

        return RestaurantMapper.toDTO(restaurant);
    }


}
