package com.eater.eater.dto.auth;

import lombok.Data;

@Data
public class BanRequest {
    private Long userId;
    private Long adminId;
    private String reasonOfBan;
}
