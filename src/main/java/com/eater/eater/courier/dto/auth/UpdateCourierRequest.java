package com.eater.eater.courier.dto.auth;

import com.eater.eater.enums.TransportType;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UpdateCourierRequest {
    private String name;
    private String email;
    private String phone;
    private MultipartFile avatar;
    private TransportType transportType;
    private String password;
}
