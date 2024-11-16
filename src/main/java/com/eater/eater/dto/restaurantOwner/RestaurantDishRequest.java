package com.eater.eater.dto.restaurantOwner;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class RestaurantDishRequest {
    private String name;
    private String description;
    private Double price;
    private Double weight;
    private MultipartFile image;
}
