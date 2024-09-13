package com.eater.eater.dto.admin;

import com.eater.eater.enums.CourierStatus;
import com.eater.eater.enums.TransportType;
import lombok.Data;

@Data
public class CouriersForAdminDTO {
    private Long id;
    private String phone;
    private String avatarUrl;
    private TransportType transportType;
    private CourierStatus courierStatus;
}
