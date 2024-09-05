package com.eater.eater.utils.mapper.auth;


import com.eater.eater.dto.auth.AdminRegistrationRequest;
import com.eater.eater.model.admin.Admin;
import org.springframework.security.crypto.password.PasswordEncoder;


public class AdminRegistrationMapper {
    public static Admin toEntity(AdminRegistrationRequest input, PasswordEncoder passwordEncoder) {
        Admin user = new Admin();
        user.setName(input.getName());
        user.setEmail(input.getEmail());
        user.setPhone(input.getPhone());
        user.setPassword(passwordEncoder.encode(input.getPassword()));

        return user;
    }
}

