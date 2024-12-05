package com.eater.eater.client.controller;

import com.eater.eater.courier.dto.CourierCoordinatesDTO;
import com.eater.eater.courier.dto.CourierRatingDTO;
import com.eater.eater.client.service.client.ClientServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/client")
@RequiredArgsConstructor
public class ClientController {
    private final ClientServiceImpl clientServiceImpl;


    @GetMapping("/courierCoordinates/{id}")
    public ResponseEntity<CourierCoordinatesDTO> getClient(@PathVariable Long id) {
        CourierCoordinatesDTO response = clientServiceImpl.getCourierCoordinates(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/rateCourier")
    public ResponseEntity<CourierRatingDTO> setRating(@RequestBody CourierRatingDTO courierRatingDTO) {
        CourierRatingDTO response = clientServiceImpl.setCourierRating(courierRatingDTO);
        return ResponseEntity.ok(response);
    }

}
