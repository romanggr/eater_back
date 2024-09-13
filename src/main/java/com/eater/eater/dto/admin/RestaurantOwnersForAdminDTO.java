package com.eater.eater.dto.admin;

import com.eater.eater.enums.RestaurantOwnerStatus;
import com.eater.eater.model.restaurantOwner.RestaurantDish;
import lombok.Data;

import java.time.LocalTime;
import java.util.List;

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
