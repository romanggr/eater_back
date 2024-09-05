package com.eater.eater.service.courier;

import com.eater.eater.model.courier.CourierRating;
import com.eater.eater.repository.courier.CourierRatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourierRatingService {
    private final CourierRatingRepository courierRatingRepository;

    @Autowired
    public CourierRatingService(CourierRatingRepository courierRatingRepository) {
        this.courierRatingRepository = courierRatingRepository;
    }

    public CourierRating getCourierRating(Long id) {
        return courierRatingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Courier's rating not found for ID: " + id));
    }

    public CourierRating addCourierRating(CourierRating courierRating) {
        if(courierRating.getRating() > 5 || courierRating.getRating() < 1){
            throw new IllegalArgumentException("Rating number should be between 1 and 5");
        }
        return courierRatingRepository.save(courierRating);
    }
}
