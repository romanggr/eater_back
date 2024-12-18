package com.eater.eater.admin.dto;

import com.eater.eater.enums.ClientStatus;
import lombok.Data;

@Data
public class ClientsForAdminDTO {
    private Long id;
    private String name;
    private String phone;
    private String avatarUrl;
    private ClientStatus clientStatus;
}
