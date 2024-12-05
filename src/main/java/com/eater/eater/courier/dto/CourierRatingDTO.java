package com.eater.eater.courier.dto;

import lombok.Data;

@Data
public class CourierRatingDTO {
    private int rating;
    private Long courierId;
    private Long clientId;
}
