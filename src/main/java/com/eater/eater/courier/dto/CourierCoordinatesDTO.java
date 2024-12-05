package com.eater.eater.courier.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CourierCoordinatesDTO {
    private Double latitude;
    private Double longitude;
    private LocalDateTime lastUpdate;
}
