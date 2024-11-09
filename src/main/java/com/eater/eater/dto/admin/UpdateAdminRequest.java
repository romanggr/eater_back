package com.eater.eater.dto.admin;

import lombok.Data;

@Data
public class UpdateAdminRequest {
    private String email;
    private String phone;
    private String name;
    private String password;
}
