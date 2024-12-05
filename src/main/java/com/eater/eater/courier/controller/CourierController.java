package com.eater.eater.courier.controller;

import com.eater.eater.courier.dto.CourierCoordinatesDTO;
import com.eater.eater.courier.dto.CourierDTO;
import com.eater.eater.courier.dto.CourierIsActiveDTO;
import com.eater.eater.courier.service.courier.CourierServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/courier")
@RequiredArgsConstructor
public class CourierController {
    private final CourierServiceImpl courierServiceImpl;


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


}
