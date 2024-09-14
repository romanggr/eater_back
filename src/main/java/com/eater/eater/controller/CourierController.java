package com.eater.eater.controller;

import com.eater.eater.dto.auth.LoginResponse;
import com.eater.eater.dto.auth.UpdatePasswordRequest;
import com.eater.eater.dto.courier.*;
import com.eater.eater.service.auth.AuthService;
import com.eater.eater.service.courier.CourierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/courier")
public class CourierController {
    private final CourierService courierService;
    private final AuthService authService;

    @Autowired
    public CourierController(CourierService courierService, AuthService authService) {
        this.courierService = courierService;
        this.authService = authService;
    }

    // Get courier data
    @GetMapping("/getCourier")
    public ResponseEntity<CourierDTO> getCourier() {
        CourierDTO response = courierService.getCourier();

        return ResponseEntity.ok(response);
    }

    // Update user data
    @PutMapping("/updateCourier")
    public ResponseEntity<LoginResponse> updateCourier(@RequestBody UpdateCourierRequest courierDTO){
        LoginResponse response = authService.updateCourier(courierDTO);
        return ResponseEntity.ok(response);
    }

    // Update password
    @PutMapping("/updatePassword")
    public ResponseEntity<LoginResponse> updatePassword(@RequestBody UpdatePasswordRequest request) {
        LoginResponse response = authService.updateCourierPassword(request);
        return ResponseEntity.ok(response);
    }

    // Update coordinates
    @PutMapping("/updateCoordinates")
    public ResponseEntity<CourierDTO> updateCoordinates(@RequestBody CourierCoordinatesDTO courierCoordinatesDTO) {
        CourierDTO response = courierService.updateCourierCoordinates(courierCoordinatesDTO);
        return ResponseEntity.ok(response);
    }

    // Set courier is ready now
    @PutMapping("/setActive")
    public ResponseEntity<CourierDTO> updateCoordinates(@RequestBody CourierIsActiveDTO courierIsActiveDTO) {
        CourierDTO response = courierService.updateIsActive(courierIsActiveDTO);
        return ResponseEntity.ok(response);
    }




    //todo Accept order
    // Confirm order delivering
    // Get order detail
    // Get orders history
    // Get payment history
}
