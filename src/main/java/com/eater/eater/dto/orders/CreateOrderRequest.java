package com.eater.eater.dto.orders;

import lombok.Data;

import java.util.List;

@Data
public class CreateOrderRequest {
    private Long restaurantId;
    private List<MenuRequest> menu;
}
