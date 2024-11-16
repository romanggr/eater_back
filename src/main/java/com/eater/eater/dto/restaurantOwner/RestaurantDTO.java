package com.eater.eater.dto.restaurantOwner;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RestaurantDTO {
    private String name;
    private String description;
    private String address;
    private Double latitude;
    private Double longitude;
    private LocalTime isOpenFrom;
    private LocalTime isOpenTo;
    private String avatarUrl;
}
