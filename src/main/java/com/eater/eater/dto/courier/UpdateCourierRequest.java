package com.eater.eater.dto.courier;

import com.eater.eater.enums.TransportType;
import lombok.Data;

@Data
public class UpdateCourierRequest {
    private String name;
    private String email;
    private String phone;
    private String avatarUrl;
    private TransportType transportType;
    private String password;
}
