package com.eater.eater.admin.dto.auth;

import lombok.Data;

@Data
public class UpdateAdminRequest {
    private String email;
    private String phone;
    private String name;
    private String currentPassword;
}
