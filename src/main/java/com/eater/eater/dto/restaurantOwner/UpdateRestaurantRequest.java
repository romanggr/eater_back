package com.eater.eater.dto.restaurantOwner;

import lombok.Data;

import java.time.LocalTime;

@Data
public class UpdateRestaurantRequest {
    private String name;
    private String description;
    private String address;
    private Double latitude;
    private Double longitude;
    private String avatarUrl;

    private LocalTime isOpenFrom;
    private LocalTime isOpenTo;
}
