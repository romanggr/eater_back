package com.eater.eater.dto.courier;

import com.eater.eater.enums.TransportType;
import lombok.*;

import java.time.LocalDateTime;

@Data
public class CourierDTO {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String avatarUrl;
    private TransportType transportType;
    private Boolean isActive;

    private double rating;

    private Double latitude;
    private Double longitude;
    private LocalDateTime lastUpdate;
}
