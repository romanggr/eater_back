package com.eater.eater.dto.courier;

import com.eater.eater.enums.TransportType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UpdateCourierRequest {
    private String name;
    private String email;
    private String phone;
    private String avatarUrl;
    private TransportType transportType;
}
