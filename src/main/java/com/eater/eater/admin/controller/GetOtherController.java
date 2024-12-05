package com.eater.eater.admin.controller;


import com.eater.eater.admin.dto.ClientsForAdminDTO;
import com.eater.eater.admin.dto.CouriersForAdminDTO;
import com.eater.eater.admin.dto.RestaurantOwnersForAdminDTO;
import com.eater.eater.admin.service.getOther.GetOtherServiceImpl;
import com.eater.eater.client.dto.ClientDTO;
import com.eater.eater.courier.dto.CourierDTO;
import com.eater.eater.restaurantOwner.dto.RestaurantOwnerDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/admin")
public class GetOtherController {
    private final GetOtherServiceImpl adminServiceImpl;


    @GetMapping("/couriers")
    public ResponseEntity<List<CouriersForAdminDTO>> getCouriers() {
        List<CouriersForAdminDTO> response = adminServiceImpl.getCouriers();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/clients")
    public ResponseEntity<List<ClientsForAdminDTO>> getClients() {
        List<ClientsForAdminDTO> response = adminServiceImpl.getClients();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/restaurantOwners")
    public ResponseEntity<List<RestaurantOwnersForAdminDTO>> getRestaurantOwners() {
        List<RestaurantOwnersForAdminDTO> response = adminServiceImpl.getRestaurantOwners();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/courierById/{id}")
    public ResponseEntity<CourierDTO> getCourierById(@PathVariable Long id) {
        CourierDTO response = adminServiceImpl.getCourierById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/clientById/{id}")
    public ResponseEntity<ClientDTO> getClientById(@PathVariable Long id) {
        ClientDTO response = adminServiceImpl.getClientById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/restaurantOwnerById/{id}")
    public ResponseEntity<RestaurantOwnerDTO> getRestaurantOwnerById(@PathVariable Long id) {
        RestaurantOwnerDTO response = adminServiceImpl.getRestaurantOwnerById(id);
        return ResponseEntity.ok(response);
    }


}
