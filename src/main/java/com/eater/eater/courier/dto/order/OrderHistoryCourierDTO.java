package com.eater.eater.courier.dto.order;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderHistoryCourierDTO {
    private Long id; // unique code for getting order
    private double totalPrice;
    private int distance;
    private double courierEarnings;
    private LocalDateTime createdAt;
    private LocalDateTime finishedAt;

    private String clientAddress;
    private String clientName;

    private String restaurantName;
    private String restaurantAddress;
}
