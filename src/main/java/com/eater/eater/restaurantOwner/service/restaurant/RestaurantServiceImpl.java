package com.eater.eater.restaurantOwner.service.restaurant;

import com.eater.eater.enums.FileCategory;
import com.eater.eater.restaurantOwner.dto.*;
import com.eater.eater.restaurantOwner.model.Restaurant;
import com.eater.eater.restaurantOwner.model.RestaurantDish;
import com.eater.eater.restaurantOwner.model.RestaurantOwner;
import com.eater.eater.restaurantOwner.repository.RestaurantDishRepository;
import com.eater.eater.restaurantOwner.repository.RestaurantRepository;
import com.eater.eater.security.SecurityUtil;
import com.eater.eater.service.S3.S3AvatarService;
import com.eater.eater.restaurantOwner.utils.RestaurantDishMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final RestaurantDishRepository restaurantDishRepository;
    private final S3AvatarService s3AvatarService;


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
        Long currentUserId = SecurityUtil.getCurrentUserId(RestaurantOwner.class);
        Restaurant restaurant = restaurantRepository.findById(currentUserId).orElseThrow(
                () -> new EntityNotFoundException("Restaurant not found"));

        List<RestaurantDish> dishes = restaurantDishRepository.findByRestaurantId(restaurant.getId());

        return dishes.stream().map(RestaurantDishMapper::toDTO).toList();
    }

}
