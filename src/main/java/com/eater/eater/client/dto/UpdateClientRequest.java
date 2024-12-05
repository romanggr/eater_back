package com.eater.eater.client.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UpdateClientRequest {
        private String name;
        private String address;
        private Double latitude;
        private Double longitude;
        private String email;
        private String phone;
        private MultipartFile avatar;
        private String password;
    }

