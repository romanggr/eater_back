package com.eater.eater.dto.courier;

import lombok.Data;

@Data
public class CourierRatingDTO {
    private int rating;
    private Long courierId;
    private Long clientId;
}
