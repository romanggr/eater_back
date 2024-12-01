package com.eater.eater.dto.orders;

import lombok.Data;

@Data
public class MenuResponse {
    private int quantity;
    private String dishName;
    private String dishImageUrl;
    private double dishPrice;
}
