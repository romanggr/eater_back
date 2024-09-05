package com.eater.eater.dto.restaurantOwner;

import com.eater.eater.model.restaurantOwner.Restaurant;
import lombok.Data;

@Data
public class RestaurantDishUpdateRequest {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Double weight;
    private String imageUrl;
    private Restaurant restaurant;
}
