package com.eater.eater.controller;

import com.eater.eater.dto.courier.*;
import com.eater.eater.service.courier.CourierServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/courier")
public class CourierController {
    private final CourierServiceImpl courierServiceImpl;

    @Autowired
    public CourierController(CourierServiceImpl courierServiceImpl) {
        this.courierServiceImpl = courierServiceImpl;
    }

    @GetMapping("/getCourier")
    public ResponseEntity<CourierDTO> getCourier() {
        CourierDTO response = courierServiceImpl.getCourier();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/updateCoordinates")
    public ResponseEntity<CourierDTO> updateCoordinates(@RequestBody CourierCoordinatesDTO courierCoordinatesDTO) {
        CourierDTO response = courierServiceImpl.updateCourierCoordinates(courierCoordinatesDTO);
        return ResponseEntity.ok(response);
    }


    @PutMapping("/setActive")
    public ResponseEntity<CourierDTO> setActive(@RequestBody CourierIsActiveDTO courierIsActiveDTO) {
        CourierDTO response = courierServiceImpl.updateIsActive(courierIsActiveDTO);
        return ResponseEntity.ok(response);
    }




    //todo Accept order
    // Confirm order delivering
    // Get order detail
    // Get orders history
    // Get payment history
}
