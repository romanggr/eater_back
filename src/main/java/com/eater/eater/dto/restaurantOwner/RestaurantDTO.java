package com.eater.eater.dto.restaurantOwner;

import com.eater.eater.model.restaurantOwner.RestaurantDish;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RestaurantDTO {
    private Long id;
    private String name;
    private String description;
    private String address;
    private Double latitude;
    private Double longitude;
    private boolean isOpenFrom;
    private boolean isOpenTo;

    private List<RestaurantDish> restaurantMenu;
}
