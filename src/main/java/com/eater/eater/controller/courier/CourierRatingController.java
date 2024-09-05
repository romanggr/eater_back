package com.eater.eater.controller.courier;

import com.eater.eater.model.courier.CourierRating;
import com.eater.eater.service.courier.CourierRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/courier-rating")
public class CourierRatingController {
    private final CourierRatingService courierRatingService;

    @Autowired
    public CourierRatingController(CourierRatingService courierRatingService) {
        this.courierRatingService = courierRatingService;
    }

    @PostMapping("/addCourierRating")
    public CourierRating addCourierRating(@RequestBody CourierRating courierRating) {
        return courierRatingService.addCourierRating(courierRating);
    }
}
