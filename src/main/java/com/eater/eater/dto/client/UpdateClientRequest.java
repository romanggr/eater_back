package com.eater.eater.dto.client;

import lombok.Data;

@Data
public class UpdateClientRequest {
        private String name;
        private String address;
        private Double latitude;
        private Double longitude;
        private String email;
        private String phone;
        private String avatarUrl;
        private String password;
    }

