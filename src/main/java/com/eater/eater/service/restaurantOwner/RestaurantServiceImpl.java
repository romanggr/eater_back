package com.eater.eater.service.restaurantOwner;

import com.eater.eater.dto.courier.UpdateCourierRequest;
import com.eater.eater.dto.restaurantOwner.*;
import com.eater.eater.enums.FileCategory;
import com.eater.eater.model.courier.Courier;
import com.eater.eater.model.restaurantOwner.Restaurant;
import com.eater.eater.model.restaurantOwner.RestaurantDish;
import com.eater.eater.model.restaurantOwner.RestaurantOwner;
import com.eater.eater.repository.restaurantOwner.RestaurantDishRepository;
import com.eater.eater.repository.restaurantOwner.RestaurantRepository;
import com.eater.eater.security.SecurityUtil;
import com.eater.eater.service.S3.S3AvatarService;
import com.eater.eater.utils.mapper.restaurantOwner.RestaurantDishMapper;
import com.eater.eater.utils.mapper.restaurantOwner.RestaurantMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final RestaurantDishRepository restaurantDishRepository;
    private final S3AvatarService s3AvatarService;


    public RestaurantServiceImpl(RestaurantRepository restaurantRepository, RestaurantDishRepository restaurantDishRepository, S3AvatarService s3AvatarService) {
        this.restaurantRepository = restaurantRepository;
        this.restaurantDishRepository = restaurantDishRepository;
        this.s3AvatarService = s3AvatarService;
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

    public RestaurantDishDTO createDish(RestaurantDishRequest request) {
        RestaurantOwner currentUser = SecurityUtil.getCurrentUser(RestaurantOwner.class);
        Restaurant restaurant = restaurantRepository.findRestaurantByRestaurantOwnerId(currentUser.getId())
                .orElseThrow(() -> new EntityNotFoundException("Restaurant not found"));
        RestaurantDish restaurantDish = RestaurantDishMapper.toEntity(
                request,
                restaurant,
                "https://eater-bucket.s3.eu-north-1.amazonaws.com/avatars/default.jpg"
        );

        restaurantDish = restaurantDishRepository.save(restaurantDish);


        if (request.getImage() != null && !request.getImage().isEmpty()) {
            String imageUrl = s3AvatarService.putObjectIntoBucket(
                    restaurantDish.getId(),
                    request.getImage(),
                    FileCategory.RESTAURANT_DISH
            );
            restaurantDish.setImageUrl(imageUrl);
        }
        restaurantDish = restaurantDishRepository.save(restaurantDish);

        return RestaurantDishMapper.toDTO(restaurantDish);
    }


    public long deleteDish(Long id) {
        if (!restaurantDishRepository.existsById(id)) {
            throw new EntityNotFoundException("Dish with ID " + id + " does not exist.");
        }

        restaurantDishRepository.deleteById(id);
        return id;
    }

    public RestaurantDishDTO updateDish(RestaurantDishUpdateRequest request) {
        RestaurantDish restaurantDish = restaurantDishRepository.findById(request.getId())
                .orElseThrow(() -> new EntityNotFoundException("Dish not found"));

        if (request.getImage() != null && !request.getImage().isEmpty()) {
            String imageUrl = s3AvatarService.putObjectIntoBucket(
                    restaurantDish.getId(),
                    request.getImage(),
                    FileCategory.RESTAURANT_DISH
            );
            restaurantDish.setImageUrl(imageUrl);
        }

        RestaurantDish updatedDish = RestaurantDishMapper.toEntityUpdateRequest(request, restaurantDish);
        updatedDish = restaurantDishRepository.save(updatedDish);

        return RestaurantDishMapper.toDTO(updatedDish);
    }


    public List<RestaurantDishDTO> getDishes() {
        RestaurantOwner currentUser = SecurityUtil.getCurrentUser(RestaurantOwner.class);
        Restaurant restaurant = restaurantRepository.findById(currentUser.getRestaurant().getId()).orElseThrow(
                () -> new EntityNotFoundException("Restaurant not found"));

        List<RestaurantDish> dishes = restaurantDishRepository.findByRestaurantId(restaurant.getId());
        List<RestaurantDishDTO> dishesDTO = dishes.stream().map(RestaurantDishMapper::toDTO).toList();
        return dishesDTO;
    }
}
