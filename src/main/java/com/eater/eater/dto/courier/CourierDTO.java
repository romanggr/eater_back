package com.eater.eater.dto.courier;

import com.eater.eater.enums.CourierStatus;
import com.eater.eater.enums.Role;
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
    private CourierStatus courierStatus;
    private Role role;

    private double rating;

    private Double latitude;
    private Double longitude;
    private LocalDateTime coordinatesLastUpdate;
}
