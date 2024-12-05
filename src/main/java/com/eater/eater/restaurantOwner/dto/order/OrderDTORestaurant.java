package com.eater.eater.restaurantOwner.dto.order;

import com.eater.eater.enums.TransportType;
import com.eater.eater.model.order.OrderMenu;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDTORestaurant {
    private Long id; // unique code for getting order
    private double restaurantEarnings;
    private List<OrderMenu> orderMenus;
    private LocalDateTime createdAt;

    private String courierName;
    private String courierPhone;
    private String courierAvatarUrl;
    private TransportType courierTransportType;
}
