package com.eater.eater.dto.orders;

import lombok.Data;

@Data
public class MenuRequest {
    private int quantity;
    private long dishId;
}
