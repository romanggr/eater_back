package com.eater.eater.dto.restaurantOwner;

import lombok.Data;

@Data
public class RestaurantDishDTO {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Double weight;
    private String imageUrl;
}
