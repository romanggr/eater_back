package com.eater.eater.courier.dto.order;

import com.eater.eater.admin.dto.order.MenuResponse;
import lombok.Data;

import java.util.List;

@Data
public class OrderDTOCourier {
    private Long id; // unique code for getting order
    private double totalPrice;
    private int distance;
    private double courierEarnings;

    private String clientAddress;
    private String clientName;
    private String clientPhone;
    private String clientAvatarUrl;
    private double clientLatitude;
    private double clientLongitude;

    private List<MenuResponse> orderMenus;
    private String restaurantPhone;
    private String restaurantName;
    private String restaurantAddress;
    private double restaurantLatitude;
    private double restaurantLongitude;
    private String restaurantAvatarUrl;
}
