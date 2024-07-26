package com.eater.eater.controller.courier;

import com.eater.eater.model.courier.CourierCoordinates;
import com.eater.eater.service.CourierCoordinatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class CourierCoordinatesController {
    private final CourierCoordinatesService courierCoordinatesService;

    @Autowired
    public CourierCoordinatesController(CourierCoordinatesService courierCoordinatesService) {
        this.courierCoordinatesService = courierCoordinatesService;
    }

//    @GetMapping("/courier-coordinates/{id}")
//    public Optional<CourierCoordinates> getCoordinates(@PathVariable Long id) {
//        return courierCoordinatesService.getCoordinates(id);
//    }
//
//    @PutMapping("/courier-coordinates/{id}")
//    public Optional<CourierCoordinates> updateCoordinates(@PathVariable Long id, @RequestBody CourierCoordinates coordinates) {
//        return courierCoordinatesService.updateCoordinates(id, coordinates);
//    }
}
