package com.eater.eater.controller;

import com.eater.eater.dto.admin.*;
import com.eater.eater.dto.auth.BanRequest;
import com.eater.eater.dto.client.ClientDTO;
import com.eater.eater.dto.courier.CourierDTO;
import com.eater.eater.dto.orders.OrderDTOAdmin;
import com.eater.eater.dto.restaurantOwner.RestaurantOwnerDTO;
import com.eater.eater.dto.statistic.StatisticDTO;
import com.eater.eater.service.admin.AdminServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {
    private final AdminServiceImpl adminServiceImpl;


    @GetMapping("/getAdmin")
    public ResponseEntity<AdminDTO> getAdmin() {
        AdminDTO response = adminServiceImpl.getAdmin();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getCouriers")
    public ResponseEntity<List<CouriersForAdminDTO>> getCouriers() {
        List<CouriersForAdminDTO> response = adminServiceImpl.getCouriers();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getClients")
    public ResponseEntity<List<ClientsForAdminDTO>> getClients() {
        List<ClientsForAdminDTO> response = adminServiceImpl.getClients();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getRestaurantOwners")
    public ResponseEntity<List<RestaurantOwnersForAdminDTO>> getRestaurantOwners() {
        List<RestaurantOwnersForAdminDTO> response = adminServiceImpl.getRestaurantOwners();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getCourierById/{id}")
    public ResponseEntity<CourierDTO> getCourierById(@PathVariable Long id) {
        CourierDTO response = adminServiceImpl.getCourierById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getClientById/{id}")
    public ResponseEntity<ClientDTO> getClientById(@PathVariable Long id) {
        ClientDTO response = adminServiceImpl.getClientById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getRestaurantOwnerById/{id}")
    public ResponseEntity<RestaurantOwnerDTO> getRestaurantOwnerById(@PathVariable Long id) {
        RestaurantOwnerDTO response = adminServiceImpl.getRestaurantOwnerById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/confirmCourier/{id}")
    public ResponseEntity<CourierDTO> confirmCourier(@PathVariable Long id) {
        CourierDTO response = adminServiceImpl.confirmCourier(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/confirmAdmin/{id}")
    public ResponseEntity<AdminDTO> confirmAdmin(@PathVariable Long id) {
        AdminDTO response = adminServiceImpl.confirmAdmin(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/banCourier")
    public ResponseEntity<Long> banCourier(BanRequest request) {
        Long response = adminServiceImpl.banCourier(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/banClient")
    public ResponseEntity<Long> banClient(BanRequest request) {
        Long response = adminServiceImpl.banClient(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/banRestaurantOwner")
    public ResponseEntity<Long> banRestaurantOwner(BanRequest request) {
        Long response = adminServiceImpl.banRestaurantOwner(request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/deleteAdmin/{id}")
    public ResponseEntity<Long> deleteAdmin(@PathVariable Long id) {
        Long response = adminServiceImpl.deleteAdmin(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getUnconfirmedAdmins")
    public ResponseEntity<List<AdminDTO>> getUnconfirmedAdmins() {
        List<AdminDTO> response = adminServiceImpl.getUnconfirmedAdmins();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/orders")
    public ResponseEntity<List<OrderDTOAdmin>> getOrders() {
        List<OrderDTOAdmin> response = adminServiceImpl.getOrders();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<OrderDTOAdmin> getOrders(@PathVariable Long id) {
        OrderDTOAdmin response = adminServiceImpl.getOrder(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/statistics")
    public ResponseEntity<StatisticDTO> getStatistics() {
        StatisticDTO response = adminServiceImpl.getStatistics();
        return ResponseEntity.ok(response);
    }

}
