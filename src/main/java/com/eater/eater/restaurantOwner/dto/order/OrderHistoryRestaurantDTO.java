package com.eater.eater.restaurantOwner.dto.order;

import com.eater.eater.model.order.OrderMenu;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderHistoryRestaurantDTO {
    private Long id; // unique code for getting order
    private double restaurantEarnings;
    private List<OrderMenu> orderMenus;
    private LocalDateTime createdAt;
}
