package com.eater.eater.service.auth;

import com.eater.eater.dto.admin.AdminDTO;
import com.eater.eater.dto.admin.UpdateAdminRequest;
import com.eater.eater.dto.auth.*;
import com.eater.eater.dto.client.ClientDTO;
import com.eater.eater.dto.client.UpdateClientRequest;
import com.eater.eater.dto.courier.CourierDTO;
import com.eater.eater.dto.courier.UpdateCourierRequest;
import com.eater.eater.dto.restaurantOwner.RestaurantOwnerDTO;
import com.eater.eater.dto.restaurantOwner.UpdateRestaurantOwnerRequest;
import com.eater.eater.enums.Role;
import com.eater.eater.model.admin.Admin;
import com.eater.eater.model.client.Client;
import com.eater.eater.model.courier.Courier;
import com.eater.eater.model.restaurantOwner.RestaurantOwner;
import com.eater.eater.repository.admin.AdminRepository;
import com.eater.eater.repository.client.ClientRepository;
import com.eater.eater.repository.courier.CourierRepository;
import com.eater.eater.repository.restaurantOwner.RestaurantOwnerRepository;
import com.eater.eater.security.JwtService;
import com.eater.eater.security.SecurityUtil;
import com.eater.eater.utils.mapper.admin.AdminMapper;
import com.eater.eater.utils.mapper.client.ClientMapper;
import com.eater.eater.utils.mapper.courier.CourierMapper;
import com.eater.eater.utils.mapper.restaurantOwner.RestaurantOwnerMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final CourierRepository courierRepository;
    private final RestaurantOwnerRepository restaurantOwnerRepository;
    private final AdminRepository adminRepository;
    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserValidationService userValidationService;

    @Autowired
    public AuthService(CourierRepository courierRepository, RestaurantOwnerRepository restaurantOwnerRepository, AdminRepository adminRepository, ClientRepository clientRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtService jwtService, UserValidationService userValidationService) {
        this.courierRepository = courierRepository;
        this.restaurantOwnerRepository = restaurantOwnerRepository;
        this.adminRepository = adminRepository;
        this.clientRepository = clientRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userValidationService = userValidationService;
    }

    //registration
    public AuthResponse<CourierDTO> courierSignup(CourierRegistrationRequest input) {
        //validation
        userValidationService.validateUser(input.getPhone(), null, input.getEmail(), null, input.getPassword(), Role.COURIER);

        Courier user = CourierMapper.authToEntity(input, passwordEncoder);
        courierRepository.save(user);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwtToken = jwtService.generateToken(user);
        AuthResponse<CourierDTO> authResponse = new AuthResponse<CourierDTO>();
        authResponse.setToken(jwtToken);

        return authResponse;
    }

    public AuthResponse<AdminDTO> adminSignup(AdminRegistrationRequest input) {
        //validation
        userValidationService.validateUser(input.getPhone(), null, input.getEmail(), null, input.getPassword(), Role.ADMIN);

        Admin user = AdminMapper.authToEntity(input, passwordEncoder);
        adminRepository.save(user);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwtToken = jwtService.generateToken(user);
        AuthResponse<AdminDTO> authResponse = new AuthResponse<AdminDTO>();
        authResponse.setToken(jwtToken);

        return authResponse;
    }

    public AuthResponse<ClientDTO> clientSignup(ClientRegistrationRequest input) {
        //validation
        userValidationService.validateUser(input.getPhone(), null, input.getEmail(), null, input.getPassword(), Role.CLIENT);

        Client user = ClientMapper.toEntity(input, passwordEncoder);
        clientRepository.save(user);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwtToken = jwtService.generateToken(user);
        AuthResponse<ClientDTO> authResponse = new AuthResponse<ClientDTO>();
        authResponse.setToken(jwtToken);

        return authResponse;
    }

    public AuthResponse<RestaurantOwnerDTO> restaurantOwnerSignup(RestaurantOwnerRegistrationRequest input) {
        //validation
        userValidationService.validateUser(input.getPhone(), null, input.getEmail(), null, input.getPassword(), Role.RESTAURANT_OWNER);
        RestaurantOwner user = RestaurantOwnerMapper.authToEntity(input, passwordEncoder);
        restaurantOwnerRepository.save(user);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwtToken = jwtService.generateToken(user);
        AuthResponse<RestaurantOwnerDTO> authResponse = new AuthResponse<RestaurantOwnerDTO>();
        authResponse.setToken(jwtToken);

        return authResponse;
    }

    //login
    public AuthResponse<CourierDTO> courierAuthenticate(LoginRequest input) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Courier courier = courierRepository.findByEmail(input.getEmail()).orElseThrow();
        String jwtToken = jwtService.generateToken(courier);
        AuthResponse<CourierDTO> authResponse = new AuthResponse<CourierDTO>();
        authResponse.setToken(jwtToken);
        return authResponse;
    }

    public AuthResponse<ClientDTO> clientAuthenticate(LoginRequest input) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Client client = clientRepository.findByEmail(input.getEmail()).orElseThrow();
        String jwtToken = jwtService.generateToken(client);
        AuthResponse<ClientDTO> authResponse = new AuthResponse<ClientDTO>();
        authResponse.setToken(jwtToken);
        return authResponse;
    }

    public AuthResponse<RestaurantOwnerDTO> restaurantOwnerAuthenticate(LoginRequest input) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        RestaurantOwner restaurantOwner = restaurantOwnerRepository.findByEmail(input.getEmail()).orElseThrow();
        String jwtToken = jwtService.generateToken(restaurantOwner);
        AuthResponse<RestaurantOwnerDTO> authResponse = new AuthResponse<RestaurantOwnerDTO>();
        authResponse.setToken(jwtToken);
        return authResponse;
    }

    public AuthResponse<AdminDTO> adminAuthenticate(LoginRequest input) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Admin admin = adminRepository.findByEmail(input.getEmail()).orElseThrow();
        String jwtToken = jwtService.generateToken(admin);
        AuthResponse<AdminDTO> authResponse = new AuthResponse<AdminDTO>();
        authResponse.setToken(jwtToken);
        return authResponse;
    }


    // update user
    public AuthResponse<AdminDTO> updateAdmin(UpdateAdminRequest request) {
        Long currentUserId = SecurityUtil.getCurrentUserId(Admin.class);
        Admin currentUser = adminRepository.findById(currentUserId)
                .orElseThrow(() -> new EntityNotFoundException("Admin not found"));
        userValidationService.validateUser(request.getPhone(), currentUser.getPhone(), request.getEmail(), currentUser.getEmail(), currentUser.getRole());

        if (!passwordEncoder.matches(request.getOldPassword(), currentUser.getPassword())) {
            throw new IllegalArgumentException("This password is incorrect");
        }

        Admin updatedUser = AdminMapper.updateRequestToEntity(request, currentUser);
        adminRepository.save(updatedUser);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getOldPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwtToken = jwtService.generateToken(updatedUser);

        AuthResponse<AdminDTO> response = new AuthResponse<AdminDTO>();
        response.setToken(jwtToken);
        return response;
    }

    public AuthResponse<CourierDTO> updateCourier(UpdateCourierRequest request) {
        Long currentUserId = SecurityUtil.getCurrentUserId(Courier.class);
        Courier currentUser = courierRepository.findById(currentUserId)
                .orElseThrow(() -> new EntityNotFoundException("Courier not found"));
        userValidationService.validateUser(request.getPhone(), currentUser.getPhone(), request.getEmail(), currentUser.getEmail(), currentUser.getRole());

        if (!passwordEncoder.matches(request.getOldPassword(), currentUser.getPassword())) {
            throw new IllegalArgumentException("This password is incorrect");
        }

        Courier updatedUser = CourierMapper.updateRequestToEntity(request, currentUser);
        courierRepository.save(updatedUser);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getOldPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwtToken = jwtService.generateToken(updatedUser);

        AuthResponse<CourierDTO> response = new AuthResponse<CourierDTO>();
        response.setToken(jwtToken);
        return response;
    }

    public AuthResponse<ClientDTO> updateClient(UpdateClientRequest request) {
        Long currentUserId = SecurityUtil.getCurrentUserId(Client.class);
        Client currentUser = clientRepository.findById(currentUserId)
                .orElseThrow(() -> new EntityNotFoundException("Client not found"));
        userValidationService.validateUser(request.getPhone(), currentUser.getPhone(), request.getEmail(), currentUser.getEmail(), currentUser.getRole());

        if (!passwordEncoder.matches(request.getOldPassword(), currentUser.getPassword())) {
            throw new IllegalArgumentException("This password is incorrect");
        }

        Client updatedUser = ClientMapper.updateRequestToEntity(request, currentUser);
        clientRepository.save(updatedUser);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getOldPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwtToken = jwtService.generateToken(updatedUser);

        AuthResponse<ClientDTO> response = new AuthResponse<ClientDTO>();
        response.setToken(jwtToken);
        return response;
    }

    public AuthResponse<RestaurantOwnerDTO> updateRestaurantOwner(UpdateRestaurantOwnerRequest request) {
        Long currentUserId = SecurityUtil.getCurrentUserId(RestaurantOwner.class);
        RestaurantOwner currentUser = restaurantOwnerRepository.findById(currentUserId)
                .orElseThrow(() -> new EntityNotFoundException("Restaurant owner not found"));
        userValidationService.validateUser(request.getPhone(), currentUser.getPhone(), request.getEmail(), currentUser.getEmail(), currentUser.getRole());

        if (!passwordEncoder.matches(request.getOldPassword(), currentUser.getPassword())) {
            throw new IllegalArgumentException("This password is incorrect");
        }

        RestaurantOwner updatedUser = RestaurantOwnerMapper.updateRequestToEntity(request, currentUser);
        restaurantOwnerRepository.save(updatedUser);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getOldPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwtToken = jwtService.generateToken(updatedUser);

        AuthResponse<RestaurantOwnerDTO> response = new AuthResponse<RestaurantOwnerDTO>();
        response.setToken(jwtToken);
        return response;
    }


    // update user password
    public AuthResponse<AdminDTO> updateAdminPassword(UpdatePasswordRequest request) {
        Long currentUserId = SecurityUtil.getCurrentUserId(Admin.class);
        Admin currentUser = adminRepository.findById(currentUserId)
                .orElseThrow(() -> new EntityNotFoundException("Admin not found"));
        userValidationService.validatePassword(request.getPassword());

        if (!passwordEncoder.matches(request.getOldPassword(), currentUser.getPassword())) {
            throw new IllegalArgumentException("This password is incorrect");
        }

        currentUser.setPassword(passwordEncoder.encode(request.getPassword()));
        Admin updatedAdmin = adminRepository.save(currentUser);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        currentUser.getEmail(),
                        request.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwtToken = jwtService.generateToken(updatedAdmin);

        AuthResponse<AdminDTO> response = new AuthResponse<AdminDTO>();
        response.setToken(jwtToken);
        return response;
    }

    public AuthResponse<CourierDTO> updateCourierPassword(UpdatePasswordRequest request) {
        Long currentUserId = SecurityUtil.getCurrentUserId(Courier.class);
        Courier currentUser = courierRepository.findById(currentUserId)
                .orElseThrow(() -> new EntityNotFoundException("Admin not found"));
        userValidationService.validatePassword(request.getPassword());

        if (!passwordEncoder.matches(request.getOldPassword(), currentUser.getPassword())) {
            throw new IllegalArgumentException("This password is incorrect");
        }

        currentUser.setPassword(passwordEncoder.encode(request.getPassword()));
        Courier updatedUser = courierRepository.save(currentUser);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        currentUser.getEmail(),
                        request.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwtToken = jwtService.generateToken(updatedUser);

        AuthResponse<CourierDTO> response = new AuthResponse<CourierDTO>();
        response.setToken(jwtToken);
        return response;
    }

    public AuthResponse<ClientDTO> updateClientPassword(UpdatePasswordRequest request) {
        Long currentUserId = SecurityUtil.getCurrentUserId(Courier.class);
        Client currentUser = clientRepository.findById(currentUserId)
                .orElseThrow(() -> new EntityNotFoundException("Admin not found"));
        userValidationService.validatePassword(request.getPassword());

        if (!passwordEncoder.matches(request.getOldPassword(), currentUser.getPassword())) {
            throw new IllegalArgumentException("This password is incorrect");
        }

        currentUser.setPassword(passwordEncoder.encode(request.getPassword()));
        Client updatedUser = clientRepository.save(currentUser);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        currentUser.getEmail(),
                        request.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwtToken = jwtService.generateToken(updatedUser);

        AuthResponse<ClientDTO> response = new AuthResponse<ClientDTO>();
        response.setToken(jwtToken);
        return response;
    }

    public AuthResponse<RestaurantOwnerDTO> updateRestaurantOwnerPassword(UpdatePasswordRequest request) {
        Long currentUserId = SecurityUtil.getCurrentUserId(Courier.class);
        RestaurantOwner currentUser = restaurantOwnerRepository.findById(currentUserId)
                .orElseThrow(() -> new EntityNotFoundException("Admin not found"));
        userValidationService.validatePassword(request.getPassword());

        if (!passwordEncoder.matches(request.getOldPassword(), currentUser.getPassword())) {
            throw new IllegalArgumentException("This password is incorrect");
        }

        currentUser.setPassword(passwordEncoder.encode(request.getPassword()));
        RestaurantOwner updatedUser = restaurantOwnerRepository.save(currentUser);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        currentUser.getEmail(),
                        request.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwtToken = jwtService.generateToken(updatedUser);

        AuthResponse<RestaurantOwnerDTO> response = new AuthResponse<RestaurantOwnerDTO>();
        response.setToken(jwtToken);
        return response;
    }

}
