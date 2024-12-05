package com.eater.eater.restaurantOwner.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class RestaurantDishUpdateRequest {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Double weight;
    private MultipartFile image;
}
