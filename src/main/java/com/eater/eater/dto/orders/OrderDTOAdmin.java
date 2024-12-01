package com.eater.eater.dto.orders;

import com.eater.eater.enums.OrderStatus;
import com.eater.eater.enums.TransportType;
import com.eater.eater.model.orders.OrderMenu;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDTOAdmin {
    private Long id; // unique code for getting order
    private Double totalPrice;
    private Double appEarnings;
    private LocalDateTime createdAt;
    private LocalDateTime finishedAt;
    private OrderStatus status;
    private int distance;

    private double courierEarnings;
    private String courierName;
    private String courierEmail;
    private String courierPhone;
    private String courierAvatarUrl;
    private TransportType courierTransportType;
    private Double courierLatitude;
    private Double courierLongitude;
    private LocalDateTime courierCoordinatesLastUpdate;

    private String clientAddress;
    private String clientName;
    private String clientPhone;
    private String clientEmail;
    private String clientAvatarUrl;
    private double clientLatitude;
    private double clientLongitude;

    private List<OrderMenu> orderMenus;
    private String restaurantName;
    private String restaurantPhone;
    private String restaurantEmail;
    private Double restaurantEarnings;
    private String restaurantAddress;
    private double restaurantLatitude;
    private double restaurantLongitude;
    private String restaurantPhotoUrl;
}
