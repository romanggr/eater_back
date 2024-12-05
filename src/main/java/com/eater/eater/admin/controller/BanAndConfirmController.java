package com.eater.eater.admin.controller;

import com.eater.eater.admin.dto.AdminDTO;
import com.eater.eater.admin.dto.BanRequest;
import com.eater.eater.admin.service.banAndConfirm.BanAndConfirmServiceImpl;
import com.eater.eater.courier.dto.CourierDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/admin")
public class BanAndConfirmController {
    private final BanAndConfirmServiceImpl banAndConfirmService;

    @PostMapping("/confirmCourier/{id}")
    public ResponseEntity<CourierDTO> confirmCourier(@PathVariable Long id) {
        CourierDTO response = banAndConfirmService.confirmCourier(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/confirmAdmin/{id}")
    public ResponseEntity<AdminDTO> confirmAdmin(@PathVariable Long id) {
        AdminDTO response = banAndConfirmService.confirmAdmin(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/banCourier")
    public ResponseEntity<Long> banCourier(BanRequest request) {
        Long response = banAndConfirmService.banCourier(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/banClient")
    public ResponseEntity<Long> banClient(BanRequest request) {
        Long response = banAndConfirmService.banClient(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/banRestaurantOwner")
    public ResponseEntity<Long> banRestaurantOwner(BanRequest request) {
        Long response = banAndConfirmService.banRestaurantOwner(request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/deleteAdmin/{id}")
    public ResponseEntity<Long> deleteAdmin(@PathVariable Long id) {
        Long response = banAndConfirmService.deleteAdmin(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/unconfirmedAdmins")
    public ResponseEntity<List<AdminDTO>> getUnconfirmedAdmins() {
        List<AdminDTO> response = banAndConfirmService.getUnconfirmedAdmins();
        return ResponseEntity.ok(response);
    }
}
