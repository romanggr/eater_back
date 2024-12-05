package com.eater.eater.admin.dto.order;

import lombok.Data;

@Data
public class MenuResponse {
    private int quantity;
    private String dishName;
    private String dishImageUrl;
    private double dishPrice;
}
