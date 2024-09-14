package com.eater.eater.security;

import com.eater.eater.exception.AdminUnverifiedException;
import com.eater.eater.model.admin.Admin;
import com.eater.eater.repository.admin.AdminRepository;
import org.springframework.stereotype.Component;

@Component
public class AdminSecurity {
    private final AdminRepository adminRepository;

    public AdminSecurity(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public void validateAdminIsConfirmed(){
        Long currentUserID = SecurityUtil.getCurrentUserId(Admin.class);
        Admin admin = adminRepository.findById(currentUserID).orElseThrow(
                () -> new IllegalArgumentException("Admin not found"));

        if(!admin.isAccepted()){
            throw new AdminUnverifiedException("Your account is not verified, please wait for another administrator to verify you");
        }
    }

}
