package com.eater.eater.dto.admin;

import com.eater.eater.enums.ClientStatus;
import com.eater.eater.enums.Role;
import lombok.Data;

@Data
public class ClientsForAdminDTO {
    private Long id;
    private String name;
    private String phone;
    private String avatarUrl;
    private ClientStatus clientStatus;
}
