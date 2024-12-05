package com.eater.eater.DTO.auth;

import lombok.Data;

@Data
public class AuthResponse<T> {
    private String token;
    private T userData;
}
