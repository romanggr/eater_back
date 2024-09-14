package com.eater.eater.dto.client;


import com.eater.eater.enums.ClientStatus;
import com.eater.eater.enums.Role;
import lombok.Data;

@Data
public class ClientDTO {
    private Long id;
    private String name;
    private String address;
    private Double latitude;
    private Double longitude;
    private String email;
    private String phone;
    private String avatarUrl;
    private ClientStatus clientStatus;
    private Role role;
}
