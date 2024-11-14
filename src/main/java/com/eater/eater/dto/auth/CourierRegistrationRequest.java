package com.eater.eater.dto.auth;

import com.eater.eater.enums.TransportType;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
public class CourierRegistrationRequest {
    private String name;
    private String email;
    private String phone;
    private String password;
    private MultipartFile avatar;
    private TransportType transportType;
    private Double coordinatesLatitude;
    private Double coordinatesLongitude;
    private LocalDateTime coordinatesLastUpdate;
}
