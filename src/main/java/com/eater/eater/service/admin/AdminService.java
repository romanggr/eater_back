package com.eater.eater.service.admin;

import com.eater.eater.dto.admin.*;
import com.eater.eater.dto.auth.BanRequest;
import com.eater.eater.dto.client.ClientDTO;
import com.eater.eater.dto.courier.CourierDTO;
import com.eater.eater.dto.restaurantOwner.RestaurantOwnerDTO;
import com.eater.eater.enums.ClientStatus;
import com.eater.eater.enums.CourierStatus;
import com.eater.eater.enums.RestaurantOwnerStatus;
import com.eater.eater.model.admin.Admin;
import com.eater.eater.model.client.Client;
import com.eater.eater.model.courier.Courier;
import com.eater.eater.model.restaurantOwner.RestaurantOwner;
import com.eater.eater.repository.admin.AdminRepository;
import com.eater.eater.repository.client.ClientRepository;
import com.eater.eater.repository.courier.CourierRepository;
import com.eater.eater.repository.restaurantOwner.RestaurantOwnerRepository;
import com.eater.eater.security.AdminSecurity;
import com.eater.eater.security.SecurityUtil;
import com.eater.eater.utils.email.EmailService;
import com.eater.eater.utils.mapper.admin.AdminMapper;
import com.eater.eater.utils.mapper.client.ClientMapper;
import com.eater.eater.utils.mapper.courier.CourierMapper;
import com.eater.eater.utils.mapper.restaurantOwner.RestaurantOwnerMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminService {

    private final CourierRepository courierRepository;
    private final ClientRepository clientRepository;
    private final AdminRepository adminRepository;
    private final RestaurantOwnerRepository restaurantOwnerRepository;
    private final AdminSecurity adminSecurity;

    public AdminService(CourierRepository courierRepository, ClientRepository clientRepository, AdminRepository adminRepository, RestaurantOwnerRepository restaurantOwnerRepository, AdminSecurity adminSecurity) {
        this.courierRepository = courierRepository;
        this.clientRepository = clientRepository;
        this.adminRepository = adminRepository;
        this.restaurantOwnerRepository = restaurantOwnerRepository;

        this.adminSecurity = adminSecurity;
    }


    public AdminDTO getAdmin() {
        Long currentUserID = SecurityUtil.getCurrentUserId(Admin.class);
        Admin admin = adminRepository.findById(currentUserID).orElseThrow(
                () -> new IllegalArgumentException("Admin not found"));

        adminSecurity.validateAdminIsConfirmed();

        return AdminMapper.toDTO(admin);
    }


    public List<CouriersForAdminDTO> getCouriers() {
        adminSecurity.validateAdminIsConfirmed();
        List<Courier> couriers = courierRepository.findAll();
        return CourierMapper.allCourierToDTO(couriers);
    }

    public List<ClientsForAdminDTO> getClients() {
        adminSecurity.validateAdminIsConfirmed();
        List<Client> clients = clientRepository.findAll();
        return ClientMapper.allClientToDTO(clients);
    }

    public List<RestaurantOwnersForAdminDTO> getRestaurantOwners() {
        adminSecurity.validateAdminIsConfirmed();
        List<RestaurantOwner> restaurantOwners = restaurantOwnerRepository.findAll();
        return RestaurantOwnerMapper.allRestaurantOwnerToDTO(restaurantOwners);
    }

    public List<CouriersForAdminDTO> getCouriersByPhone(String phone) {
        adminSecurity.validateAdminIsConfirmed();
        List<Courier> couriers = courierRepository.findAllByPhone(phone);
        return CourierMapper.allCourierToDTO(couriers);
    }

    public List<ClientsForAdminDTO> getClientsByPhone(String phone) {
        adminSecurity.validateAdminIsConfirmed();
        List<Client> clients = clientRepository.findAllByPhone(phone);
        return ClientMapper.allClientToDTO(clients);
    }

    public List<RestaurantOwnersForAdminDTO> getRestaurantOwnerByPhone(String phone) {
        adminSecurity.validateAdminIsConfirmed();
        List<RestaurantOwner> restaurantOwners = restaurantOwnerRepository.findAllByPhone(phone);
        return RestaurantOwnerMapper.allRestaurantOwnerToDTO(restaurantOwners);
    }

    public CourierDTO getCourierById(Long id) {
        adminSecurity.validateAdminIsConfirmed();
        Courier courier = courierRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Cannot find user with id " + id));
        return CourierMapper.toDTO(courier);
    }

    public ClientDTO getClientById(Long id) {
        adminSecurity.validateAdminIsConfirmed();
        Client client = clientRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Cannot find user with id " + id));
        return ClientMapper.toDTO(client);
    }

    public RestaurantOwnerDTO getRestaurantOwnerById(Long id) {
        adminSecurity.validateAdminIsConfirmed();
        RestaurantOwner restaurantOwner = restaurantOwnerRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Cannot find user with id " + id));
        return RestaurantOwnerMapper.toDTO(restaurantOwner);
    }

    public CourierDTO confirmCourier(Long id) {
        adminSecurity.validateAdminIsConfirmed();
        Courier courier = courierRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Cannot find user with id " + id));
        courier.setCourierStatus(CourierStatus.OFF_DUTY);
        Courier updatedCourier = courierRepository.save(courier);

        return CourierMapper.toDTO(updatedCourier);
    }

    public AdminDTO confirmAdmin(Long id) {
        adminSecurity.validateAdminIsConfirmed();
        Admin admin = adminRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Cannot find user with id " + id));
        admin.setAccepted(true);
        Admin updatedAdmin = adminRepository.save(admin);
        return AdminMapper.toDTO(updatedAdmin);
    }

    public Long banCourier(BanRequest request) {
        adminSecurity.validateAdminIsConfirmed();
        Courier courier = courierRepository.findById(request.getUserId()).orElseThrow(
                () -> new IllegalArgumentException("Courier with id " + request.getUserId() + " not found"));
        courier.setCourierStatus(CourierStatus.BANNED);
        EmailService.banAccount(courier.getEmail(), request.getReasonOfBan(), request.getAdminId());

        return courier.getId();
    }

    public Long banClient(BanRequest request) {
        adminSecurity.validateAdminIsConfirmed();
        Client client = clientRepository.findById(request.getUserId()).orElseThrow(
                () -> new IllegalArgumentException("Client with id " + request.getUserId() + " not found"));
        client.setClientStatus(ClientStatus.BANNED);
        EmailService.banAccount(client.getEmail(), request.getReasonOfBan(), request.getAdminId());

        return client.getId();
    }

    public Long banRestaurantOwner(BanRequest request) {
        adminSecurity.validateAdminIsConfirmed();
        RestaurantOwner restaurantOwner = restaurantOwnerRepository.findById(request.getUserId()).orElseThrow(
                () -> new IllegalArgumentException("Restaurant owner with id " + request.getUserId() + " not found"));
        restaurantOwner.setRestaurantOwnerStatus(RestaurantOwnerStatus.BANNED);
        EmailService.banAccount(restaurantOwner.getEmail(), request.getReasonOfBan(), request.getAdminId());

        return restaurantOwner.getId();
    }

    public Long deleteAdmin(Long id) {
        adminSecurity.validateAdminIsConfirmed();
        if (!adminRepository.existsById(id)) throw new IllegalArgumentException("Admin with id " + id + " not found");

        adminRepository.deleteById(id);
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
