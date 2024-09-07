package com.eater.eater.controller;

import com.eater.eater.dto.auth.UpdatePasswordRequest;
import com.eater.eater.dto.client.ClientDTO;
import com.eater.eater.dto.client.UpdateClientRequest;
import com.eater.eater.dto.courier.CourierCoordinatesDTO;
import com.eater.eater.dto.courier.CourierRatingDTO;
import com.eater.eater.service.client.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/client")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/getClient")
    public ResponseEntity<ClientDTO> getClient() {
        ClientDTO response = clientService.getClient();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/updateClient")
    public ResponseEntity<ClientDTO> updateClient(@RequestBody UpdateClientRequest updateClientRequest) {
        ClientDTO response = clientService.updateClient(updateClientRequest);
        return ResponseEntity.ok(response);
    }
    @PutMapping("/updatePassword")
    public ResponseEntity<ClientDTO> updatePassword(@RequestBody UpdatePasswordRequest request) {
        ClientDTO response = clientService.updateCourierPassword(request);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/getCourierCoordinates/{id}")
    public ResponseEntity<CourierCoordinatesDTO> getClient(@PathVariable Long id) {
        CourierCoordinatesDTO response = clientService.getCourierCoordinates(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/rateCourier")
    public ResponseEntity<CourierRatingDTO> setRating(@RequestBody CourierRatingDTO courierRatingDTO) {
        CourierRatingDTO response = clientService.setCourierRating(courierRatingDTO);
        System.out.println(123);
        return ResponseEntity.ok(response);
    }
}
