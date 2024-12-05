package com.eater.eater.client.dto.order;

import lombok.Data;

@Data
public class MenuRequest {
    private int quantity;
    private long dishId;
}
