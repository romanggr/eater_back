package com.eater.eater.client.dto.order;

import lombok.Data;

import java.util.List;

@Data
public class CreateOrderRequest {
    private Long restaurantId;
    private List<MenuRequest> menu;
}
