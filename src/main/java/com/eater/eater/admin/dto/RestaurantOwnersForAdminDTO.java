package com.eater.eater.admin.dto;

import com.eater.eater.enums.RestaurantOwnerStatus;
import lombok.Data;

import java.time.LocalTime;

@Data
public class RestaurantOwnersForAdminDTO {
    private Long id;
    private String name;
    private LocalTime isOpenFrom;
    private LocalTime isOpenTo;
    private String avatarUrl;
    private String phoneNumber;
    private RestaurantOwnerStatus restaurantOwnerStatus;
}
