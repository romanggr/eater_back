package com.eater.eater.dto.courier;

import com.eater.eater.model.courier.Courier;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
public class CourierRatingDTO {
    private Long id;
    private int rating;
    private Long courierId;
    private Long clientId;
}
