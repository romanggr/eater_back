package com.eater.eater.admin.dto;

import com.eater.eater.enums.Role;
import lombok.Data;

@Data
public class AdminDTO {
    private Long id;
    private String email;
    private String phone;
    private String name;
    private boolean isAccepted;
    private Role role;
}
