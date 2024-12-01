package com.eater.eater.dto.orders;

import com.eater.eater.enums.TransportType;
import com.eater.eater.model.orders.OrderMenu;
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
