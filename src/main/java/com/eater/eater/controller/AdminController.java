package com.eater.eater.controller;

import com.eater.eater.dto.admin.*;
import com.eater.eater.dto.auth.BanRequest;
import com.eater.eater.dto.auth.UpdatePasswordRequest;
import com.eater.eater.dto.client.ClientDTO;
import com.eater.eater.dto.courier.CourierDTO;
import com.eater.eater.dto.restaurantOwner.RestaurantOwnerDTO;
import com.eater.eater.model.admin.Admin;
import com.eater.eater.model.restaurantOwner.RestaurantOwner;
import com.eater.eater.service.admin.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    // get admin
    @GetMapping("/getAdmin")
    public ResponseEntity<AdminDTO> getAdmin() {
        AdminDTO response = adminService.getAdmin();
        return ResponseEntity.ok(response);
    }

    // update admin
    @PutMapping("/updateAdmin")
    public ResponseEntity<AdminDTO> updateAdmin(@RequestBody UpdateAdminRequest request) {
        AdminDTO response = adminService.updateAdmin(request);
        return ResponseEntity.ok(response);
    }

    // update password
    @PutMapping("/updatePassword")
    public ResponseEntity<AdminDTO> updatePassword(@RequestBody UpdatePasswordRequest request) {
        AdminDTO response = adminService.updatePassword(request);
        return ResponseEntity.ok(response);
    }


    // get all couriers (get only necessary info like email and avatar)
    @GetMapping("/getCouriers")
    public ResponseEntity<List<CouriersForAdminDTO>> getCouriers() {
        List<CouriersForAdminDTO> response = adminService.getCouriers();
        return ResponseEntity.ok(response);
    }

    // get all client with pagination (get only necessary info like email and avatar)
    @GetMapping("/getClients")
    public ResponseEntity<List<ClientsForAdminDTO>> getClients() {
        List<ClientsForAdminDTO> response = adminService.getClients();
        return ResponseEntity.ok(response);
    }

    // get all restaurant owners with pagination (get only necessary info like email and avatar)
    @GetMapping("/getRestaurantOwners")
    public ResponseEntity<List<RestaurantOwnersForAdminDTO>> getRestaurantOwners() {
        List<RestaurantOwnersForAdminDTO> response = adminService.getRestaurantOwners();
        return ResponseEntity.ok(response);
    }

    // get couriers by phone (get only necessary info like email and avatar)
    @GetMapping("/getCouriersByPhone/{phone}")
    public ResponseEntity<List<CouriersForAdminDTO>> getCouriersByPhone(@PathVariable String phone) {
        List<CouriersForAdminDTO> response = adminService.getCouriersByPhone(phone);
        return ResponseEntity.ok(response);
    }

    // get clients by phone (get only necessary info like email and avatar)
    @GetMapping("/getClientsByPhone/{phone}")
    public ResponseEntity<List<ClientsForAdminDTO>> getClientsByPhone(@PathVariable String phone) {
        List<ClientsForAdminDTO> response = adminService.getClientsByPhone(phone);
        return ResponseEntity.ok(response);
    }

    // get restaurant owners by phone (get only necessary info like email and avatar)
    @GetMapping("/getRestaurantOwnersByPhone/{phone}")
    public ResponseEntity<List<RestaurantOwnersForAdminDTO>> getRestaurantOwnersByPhone(@PathVariable String phone) {
        List<RestaurantOwnersForAdminDTO> response = adminService.getRestaurantOwnerByPhone(phone);
        return ResponseEntity.ok(response);
    }

    // get courier by id (get full info)
    @GetMapping("/getCourierById/{id}")
    public ResponseEntity<CourierDTO> getCourierById(@PathVariable Long id) {
        CourierDTO response = adminService.getCourierById(id);
        return ResponseEntity.ok(response);
    }

    // get client by id (get full info)
    @GetMapping("/getClientById/{id}")
    public ResponseEntity<ClientDTO> getClientById(@PathVariable Long id) {
        ClientDTO response = adminService.getClientById(id);
        return ResponseEntity.ok(response);
    }

    // get restaurant owner by id (get full info)
    @GetMapping("/getRestaurantOwnerById/{id}")
    public ResponseEntity<RestaurantOwnerDTO> getRestaurantOwnerById(@PathVariable Long id) {
        RestaurantOwnerDTO response = adminService.getRestaurantOwnerById(id);
        return ResponseEntity.ok(response);
    }

    //    confirm courier
    @PostMapping("/confirmCourier/{id}")
    public ResponseEntity<CourierDTO> confirmCourier(@PathVariable Long id) {
        CourierDTO response = adminService.confirmCourier(id);
        return ResponseEntity.ok(response);
    }


    // confirm admin
    @PostMapping("/confirmAdmin/{id}")
    public ResponseEntity<AdminDTO> confirmAdmin(@PathVariable Long id) {
        AdminDTO response = adminService.confirmAdmin(id);
        return ResponseEntity.ok(response);
    }


    // ban courier (when you ban you also send email that his account was banned)
    @PutMapping("/banCourier")
    public ResponseEntity<Long> banCourier(BanRequest request) {
        Long response = adminService.banCourier(request);
        return ResponseEntity.ok(response);
    }

    // ban client (when you ban you also send email that his account was banned)
    @PutMapping("/banClient")
    public ResponseEntity<Long> banClient(BanRequest request) {
        Long response = adminService.banClient(request);
        return ResponseEntity.ok(response);
    }

    // ban restaurant owner (when you ban you also send email that his account was banned)
    @PutMapping("/banRestaurantOwner")
    public ResponseEntity<Long> banRestaurantOwner(BanRequest request) {
        Long response = adminService.banRestaurantOwner(request);
        return ResponseEntity.ok(response);
    }

    // delete admin
    @DeleteMapping("/deleteAdmin/{id}")
    public ResponseEntity<Long> deleteAdmin(@PathVariable Long id) {
        Long response = adminService.deleteAdmin(id);
        return ResponseEntity.ok(response);
    }

    // get unconfirmed admins
    @GetMapping("/getUnconfirmedAdmins")
    public ResponseEntity<List<AdminDTO>> getUnconfirmedAdmins() {
        List<AdminDTO> response = adminService.getUnconfirmedAdmins();
        return ResponseEntity.ok(response);
    }

    //todo Get general data like order quantity , earning per day etc.
}
