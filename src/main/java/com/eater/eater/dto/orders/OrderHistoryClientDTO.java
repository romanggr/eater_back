package com.eater.eater.dto.orders;

import com.eater.eater.model.orders.OrderMenu;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderHistoryClientDTO {
    private Long id; // unique code for getting order
    private double totalPrice;
    private LocalDateTime createdAt;
    private LocalDateTime finishedAt;

    private String restaurantName;
    private String restaurantAddress;
    private List<OrderMenu> orderMenu;
}
