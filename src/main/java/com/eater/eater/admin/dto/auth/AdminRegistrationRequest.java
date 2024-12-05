package com.eater.eater.admin.dto.auth;

import lombok.Data;

@Data
public class AdminRegistrationRequest {
    private String name;
    private String email;
    private String phone;
    private String password;
    private Integer emailCode;
}
