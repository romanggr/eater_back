package com.eater.eater.service.restaurantOwner;

import com.eater.eater.dto.restaurantOwner.CreateRestaurantRequest;
import com.eater.eater.dto.restaurantOwner.RestaurantDTO;
import com.eater.eater.dto.restaurantOwner.RestaurantOwnerDTO;
import com.eater.eater.enums.FileCategory;
import com.eater.eater.exception.RestaurantAlreadyCreatedException;
import com.eater.eater.model.restaurantOwner.Restaurant;
import com.eater.eater.model.restaurantOwner.RestaurantOwner;
import com.eater.eater.repository.restaurantOwner.RestaurantOwnerRepository;
import com.eater.eater.repository.restaurantOwner.RestaurantRepository;
import com.eater.eater.security.SecurityUtil;
import com.eater.eater.service.S3.S3AvatarService;
import com.eater.eater.utils.mapper.restaurantOwner.RestaurantMapper;
import com.eater.eater.utils.mapper.restaurantOwner.RestaurantOwnerMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class RestaurantOwnerServiceImpl implements RestaurantOwnerService {
    private final RestaurantOwnerRepository restaurantOwnerRepository;
    private final RestaurantRepository restaurantRepository;
    private final S3AvatarService s3AvatarService;


    public RestaurantOwnerServiceImpl(RestaurantOwnerRepository restaurantOwnerRepository, RestaurantRepository restaurantRepository, S3AvatarService s3AvatarService) {
        this.restaurantOwnerRepository = restaurantOwnerRepository;
        this.restaurantRepository = restaurantRepository;
        this.s3AvatarService = s3AvatarService;
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
    public RestaurantDTO createRestaurant(CreateRestaurantRequest request) {
        System.out.println(1);
        RestaurantOwner currentUser = SecurityUtil.getCurrentUser(RestaurantOwner.class);
        RestaurantOwner restaurantOwner = restaurantOwnerRepository.findById(currentUser.getId())
                .orElseThrow(() -> new EntityNotFoundException("Restaurant Owner not found"));
        System.out.println(1);

        if (restaurantOwner.getRestaurant() != null) {
            throw new RestaurantAlreadyCreatedException("You can be the owner of only 1 restaurant");
        }
        System.out.println(1);

        Restaurant restaurant = RestaurantMapper.restaurantToEntity(
                request,
                restaurantOwner,
                "https://eater-bucket.s3.eu-north-1.amazonaws.com/avatars/default.jpg"
        );
        System.out.println(1);

        restaurant = restaurantRepository.save(restaurant);
        System.out.println(1);

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

}
