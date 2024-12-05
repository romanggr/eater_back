package com.eater.eater.DTO.auth;

import lombok.Data;

@Data
public class UpdatePasswordRequest {
    private String newPassword;
    private String currentPassword;
}
