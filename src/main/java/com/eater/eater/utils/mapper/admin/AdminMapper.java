package com.eater.eater.utils.mapper.admin;

import com.eater.eater.dto.admin.*;
import com.eater.eater.dto.auth.AdminRegistrationRequest;
import com.eater.eater.model.admin.Admin;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AdminMapper {
    public static AdminDTO toDTO(Admin admin) {
        if (admin == null) return null;

        AdminDTO adminDTO = new AdminDTO();
        adminDTO.setId(admin.getId());
        adminDTO.setAccepted(admin.isAccepted());
        adminDTO.setName(admin.getName());
        adminDTO.setPhone(admin.getPhone());
        adminDTO.setEmail(admin.getEmail());
        adminDTO.setRole(admin.getRole());

        return adminDTO;
    }

    public static Admin authToEntity(AdminRegistrationRequest input, PasswordEncoder passwordEncoder) {
        Admin user = new Admin();
        user.setName(input.getName());
        user.setEmail(input.getEmail());
        user.setPhone(input.getPhone());
        user.setPassword(passwordEncoder.encode(input.getPassword()));

        return user;
    }

    public static Admin updateRequestToEntity(UpdateAdminRequest request, Admin currentAdmin) {
        if (request == null || currentAdmin == null) return null;

        currentAdmin.setEmail(request.getEmail());
        currentAdmin.setPhone(request.getPhone());
        currentAdmin.setName(request.getName());

        return currentAdmin;
    }

}
