package com.eater.eater.dto.auth;

import lombok.Data;

@Data
public class UpdatePasswordRequest {
    private String newPassword;
    private String currentPassword;
}
