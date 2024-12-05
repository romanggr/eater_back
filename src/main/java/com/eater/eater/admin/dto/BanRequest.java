package com.eater.eater.admin.dto;

import lombok.Data;

@Data
public class BanRequest {
    private Long userId;
    private Long adminId;
    private String reasonOfBan;
}
