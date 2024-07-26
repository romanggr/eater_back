package com.eater.eater.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrdersDTO {
    private Long id;
    private Double price;
    private String restaurantName;
    private String restaurantAddress;
    private LocalDateTime startTime;
    private LocalDateTime finishTime;
    private Double distance;

    private Long clientId;
    private String clientAddress;
    private String clientName;
    private String clientAvatarUrl;
    private String clientPhone;
    private String clientEmail;

    private Long courierId;
    private String courierName;
    private String courierEmail;
    private String courierPhone;
    private String courierAvatarUrl;

    private Double courierLatitude;
    private Double courierLongitude;
    private LocalDateTime courierCoordinatesLastUpdate;
}
