package com.eater.eater.admin.service.banAndConfirm;

import com.eater.eater.admin.dto.AdminDTO;
import com.eater.eater.admin.dto.BanRequest;
import com.eater.eater.admin.model.Admin;
import com.eater.eater.admin.repository.AdminRepository;
import com.eater.eater.admin.utils.AdminMapper;
import com.eater.eater.client.model.Client;
import com.eater.eater.client.repository.ClientRepository;
import com.eater.eater.courier.dto.CourierDTO;
import com.eater.eater.courier.model.Courier;
import com.eater.eater.courier.repository.CourierRepository;
import com.eater.eater.courier.utils.CourierMapper;
import com.eater.eater.email.service.EmailScripts;
import com.eater.eater.enums.ClientStatus;
import com.eater.eater.enums.CourierStatus;
import com.eater.eater.enums.RestaurantOwnerStatus;
import com.eater.eater.restaurantOwner.model.RestaurantOwner;
import com.eater.eater.restaurantOwner.repository.RestaurantOwnerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BanAndConfirmServiceImpl implements BanAndConfirmService {
    private final EmailScripts emailScripts;
    private final CourierRepository courierRepository;
    private final ClientRepository clientRepository;
    private final RestaurantOwnerRepository restaurantOwnerRepository;
    private final AdminRepository adminRepository;


    public CourierDTO confirmCourier(Long id) {
        Courier courier = courierRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Cannot find user with id " + id));
        courier.setCourierStatus(CourierStatus.OFF_DUTY);
        Courier updatedCourier = courierRepository.save(courier);

        // send email to user
        emailScripts.confirmUserByAdmin(courier.getEmail(), courier.getName());

        return CourierMapper.toDTO(updatedCourier);
    }

    public AdminDTO confirmAdmin(Long id) {
        Admin admin = adminRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Cannot find user with id " + id));
        admin.setAccepted(true);
        Admin updatedAdmin = adminRepository.save(admin);

        // send email to user
        emailScripts.confirmUserByAdmin(admin.getEmail(), admin.getName());

        return AdminMapper.toDTO(updatedAdmin);
    }

    public Long banCourier(BanRequest request) {
        Courier courier = courierRepository.findById(request.getUserId()).orElseThrow(
                () -> new IllegalArgumentException("Courier with id " + request.getUserId() + " not found"));
        courier.setCourierStatus(CourierStatus.BANNED);

        // send email to user
        emailScripts.banUserByAdmin(courier.getEmail(), courier.getName(), request.getReasonOfBan());

        return courier.getId();
    }

    public Long banClient(BanRequest request) {
        Client client = clientRepository.findById(request.getUserId()).orElseThrow(
                () -> new IllegalArgumentException("Client with id " + request.getUserId() + " not found"));
        client.setClientStatus(ClientStatus.BANNED);

        // send email to user
        emailScripts.banUserByAdmin(client.getEmail(), client.getName(), request.getReasonOfBan());

        return client.getId();
    }

    public Long banRestaurantOwner(BanRequest request) {
        RestaurantOwner restaurantOwner = restaurantOwnerRepository.findById(request.getUserId()).orElseThrow(
                () -> new IllegalArgumentException("Restaurant owner with id " + request.getUserId() + " not found"));
        restaurantOwner.setRestaurantOwnerStatus(RestaurantOwnerStatus.BANNED);

        // send email to user
        emailScripts.banUserByAdmin(restaurantOwner.getEmail(), restaurantOwner.getName(), request.getReasonOfBan());

        return restaurantOwner.getId();
    }

    public Long deleteAdmin(Long id) {
        Admin admin = adminRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Admin with id " + id + " not found"));

        adminRepository.delete(admin);

        // send email to user
        emailScripts.deleteAdminByAdmin(admin.getEmail(), admin.getName());
        return id;
    }

    public List<AdminDTO> getUnconfirmedAdmins() {
        List<Admin> admins = adminRepository.findAll();

        return admins.stream()
                .filter(admin -> !admin.isAccepted())
                .map(AdminMapper::toDTO)
                .collect(Collectors.toList());
    }
}
