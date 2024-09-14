package com.eater.eater.service.auth;

import com.eater.eater.dto.admin.UpdateAdminRequest;
import com.eater.eater.dto.auth.*;
import com.eater.eater.dto.client.UpdateClientRequest;
import com.eater.eater.dto.courier.UpdateCourierRequest;
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
    public LoginResponse courierSignup(CourierRegistrationRequest input) {
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
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);

        return loginResponse;
    }

    public LoginResponse adminSignup(AdminRegistrationRequest input) {
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
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);

        return loginResponse;
    }

    public LoginResponse clientSignup(ClientRegistrationRequest input) {
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
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);

        return loginResponse;
    }

    public LoginResponse restaurantOwnerSignup(RestaurantOwnerRegistrationRequest input) {
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
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);

        return loginResponse;
    }

    //login
    public LoginResponse courierAuthenticate(LoginRequest input) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Courier courier = courierRepository.findByEmail(input.getEmail()).orElseThrow();
        String jwtToken = jwtService.generateToken(courier);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        return loginResponse;
    }

    public LoginResponse clientAuthenticate(LoginRequest input) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Client client = clientRepository.findByEmail(input.getEmail()).orElseThrow();
        String jwtToken = jwtService.generateToken(client);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        return loginResponse;
    }

    public LoginResponse restaurantOwnerAuthenticate(LoginRequest input) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        RestaurantOwner restaurantOwner = restaurantOwnerRepository.findByEmail(input.getEmail()).orElseThrow();
        String jwtToken = jwtService.generateToken(restaurantOwner);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        return loginResponse;
    }

    public LoginResponse adminAuthenticate(LoginRequest input) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Admin admin = adminRepository.findByEmail(input.getEmail()).orElseThrow();
        String jwtToken = jwtService.generateToken(admin);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        return loginResponse;
    }


    // update user
    public LoginResponse updateAdmin(UpdateAdminRequest request) {
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

        LoginResponse response = new LoginResponse();
        response.setToken(jwtToken);
        return response;
    }

    public LoginResponse updateCourier(UpdateCourierRequest request) {
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

        LoginResponse response = new LoginResponse();
        response.setToken(jwtToken);
        return response;
    }

    public LoginResponse updateClient(UpdateClientRequest request) {
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

        LoginResponse response = new LoginResponse();
        response.setToken(jwtToken);
        return response;
    }

    public LoginResponse updateRestaurantOwner(UpdateRestaurantOwnerRequest request) {
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

        LoginResponse response = new LoginResponse();
        response.setToken(jwtToken);
        return response;
    }


    // update user password
    public LoginResponse updateAdminPassword(UpdatePasswordRequest request) {
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

        LoginResponse response = new LoginResponse();
        response.setToken(jwtToken);
        return response;
    }

    public LoginResponse updateCourierPassword(UpdatePasswordRequest request) {
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

        LoginResponse response = new LoginResponse();
        response.setToken(jwtToken);
        return response;
    }

    public LoginResponse updateClientPassword(UpdatePasswordRequest request) {
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

        LoginResponse response = new LoginResponse();
        response.setToken(jwtToken);
        return response;
    }

    public LoginResponse updateRestaurantOwnerPassword(UpdatePasswordRequest request) {
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

        LoginResponse response = new LoginResponse();
        response.setToken(jwtToken);
        return response;
    }

}
