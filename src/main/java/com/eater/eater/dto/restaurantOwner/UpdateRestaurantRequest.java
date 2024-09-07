package com.eater.eater.dto.restaurantOwner;

import com.eater.eater.model.restaurantOwner.RestaurantDish;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Data
public class UpdateRestaurantRequest {
    private String name;
    private String description;
    private String address;
    private Double latitude;
    private Double longitude;

    private LocalTime isOpenFrom;
    private LocalTime isOpenTo;
}
