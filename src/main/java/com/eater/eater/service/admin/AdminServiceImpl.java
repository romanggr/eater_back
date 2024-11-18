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
import com.eater.eater.security.SecurityUtil;
import com.eater.eater.service.email.EmailScripts;
import com.eater.eater.utils.fakeData.FakeData;
import com.eater.eater.utils.mapper.admin.AdminMapper;
import com.eater.eater.utils.mapper.client.ClientMapper;
import com.eater.eater.utils.mapper.courier.CourierMapper;
import com.eater.eater.utils.mapper.restaurantOwner.RestaurantOwnerMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService {
    private final FakeData fakeData;
    private final CourierRepository courierRepository;
    private final ClientRepository clientRepository;
    private final AdminRepository adminRepository;
    private final RestaurantOwnerRepository restaurantOwnerRepository;
    private final EmailScripts emailScripts;

    public AdminServiceImpl(FakeData fakeData, CourierRepository courierRepository, ClientRepository clientRepository, AdminRepository adminRepository, RestaurantOwnerRepository restaurantOwnerRepository, EmailScripts emailScripts) {
        this.fakeData = fakeData;
        this.courierRepository = courierRepository;
        this.clientRepository = clientRepository;
        this.adminRepository = adminRepository;
        this.restaurantOwnerRepository = restaurantOwnerRepository;
        this.emailScripts = emailScripts;
    }


    public AdminDTO getAdmin() {
        Long currentUserID = SecurityUtil.getCurrentUserId(Admin.class);
        Admin admin = adminRepository.findById(currentUserID).orElseThrow(
                () -> new EntityNotFoundException("Admin not found"));

        return AdminMapper.toDTO(admin);
    }


    public List<CouriersForAdminDTO> getCouriers() {
        List<Courier> couriers = courierRepository.findAll();
        return CourierMapper.allCourierToDTO(couriers);
    }

    public List<ClientsForAdminDTO> getClients() {
        List<Client> clients = clientRepository.findAll();
        return ClientMapper.allClientToDTO(clients);
    }

    public List<RestaurantOwnersForAdminDTO> getRestaurantOwners() {
        List<RestaurantOwner> restaurantOwners = restaurantOwnerRepository.findAll();
        return RestaurantOwnerMapper.allRestaurantOwnerToDTO(restaurantOwners);
    }


    public CourierDTO getCourierById(Long id) {
        Courier courier = courierRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Cannot find user with id " + id));
        return CourierMapper.toDTO(courier);
    }

    public ClientDTO getClientById(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Cannot find user with id " + id));
        return ClientMapper.toDTO(client);
    }

    public RestaurantOwnerDTO getRestaurantOwnerById(Long id) {
        RestaurantOwner restaurantOwner = restaurantOwnerRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Cannot find user with id " + id));
        return RestaurantOwnerMapper.toDTO(restaurantOwner);
    }

    public CourierDTO confirmCourier(Long id) {
        Courier courier = courierRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Cannot find user with id " + id));
        courier.setCourierStatus(CourierStatus.OFF_DUTY);
        Courier updatedCourier = courierRepository.save(courier);

        // send email to courier
        emailScripts.confirmUserByAdmin(courier.getEmail(), courier.getName());

        return CourierMapper.toDTO(updatedCourier);
    }

    public AdminDTO confirmAdmin(Long id) {
        Admin admin = adminRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Cannot find user with id " + id));
        admin.setAccepted(true);
        Admin updatedAdmin = adminRepository.save(admin);

        // send email to courier
        emailScripts.confirmUserByAdmin(admin.getEmail(), admin.getName());

        return AdminMapper.toDTO(updatedAdmin);
    }

    public Long banCourier(BanRequest request) {
        Courier courier = courierRepository.findById(request.getUserId()).orElseThrow(
                () -> new IllegalArgumentException("Courier with id " + request.getUserId() + " not found"));
        courier.setCourierStatus(CourierStatus.BANNED);

        // send email to courier
        emailScripts.banUserByAdmin(courier.getEmail(), courier.getName(), request.getReasonOfBan());

        return courier.getId();
    }

    public Long banClient(BanRequest request) {
        Client client = clientRepository.findById(request.getUserId()).orElseThrow(
                () -> new IllegalArgumentException("Client with id " + request.getUserId() + " not found"));
        client.setClientStatus(ClientStatus.BANNED);

        // send email to courier
        emailScripts.banUserByAdmin(client.getEmail(), client.getName(), request.getReasonOfBan());

        return client.getId();
    }

    public Long banRestaurantOwner(BanRequest request) {
        RestaurantOwner restaurantOwner = restaurantOwnerRepository.findById(request.getUserId()).orElseThrow(
                () -> new IllegalArgumentException("Restaurant owner with id " + request.getUserId() + " not found"));
        restaurantOwner.setRestaurantOwnerStatus(RestaurantOwnerStatus.BANNED);

        // send email to courier
        emailScripts.banUserByAdmin(restaurantOwner.getEmail(), restaurantOwner.getName(), request.getReasonOfBan());

        return restaurantOwner.getId();
    }

    public Long deleteAdmin(Long id) {
        Admin admin = adminRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Admin with id " + id + " not found"));

        adminRepository.delete(admin);

        // send email to courier
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

    public void generateFakeData() {
        adminRepository.saveAll(fakeData.generateFakeAdmins(5));
        courierRepository.saveAll(fakeData.getFakeCouriers(5));
        clientRepository.saveAll(fakeData.generateFakeClients(5));
        restaurantOwnerRepository.saveAll(fakeData.generateFakeRestaurantOwners(5));

    }
}
