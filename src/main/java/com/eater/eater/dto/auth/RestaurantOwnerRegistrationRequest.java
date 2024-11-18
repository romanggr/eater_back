package com.eater.eater.dto.auth;

import lombok.Data;

@Data
public class RestaurantOwnerRegistrationRequest {
    private String name;
    private String email;
    private String phone;
    private String password;
    private int emailCode;
}
