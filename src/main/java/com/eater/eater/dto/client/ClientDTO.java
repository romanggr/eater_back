package com.eater.eater.dto.client;


import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
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
}
