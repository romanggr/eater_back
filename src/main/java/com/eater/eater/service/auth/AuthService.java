package com.eater.eater.service.auth;

import com.eater.eater.dto.auth.*;
import com.eater.eater.model.admin.Admin;
import com.eater.eater.model.client.Client;
import com.eater.eater.model.courier.Courier;
import com.eater.eater.model.restaurantOwner.RestaurantOwner;
import com.eater.eater.repository.admin.AdminRepository;
import com.eater.eater.repository.client.ClientRepository;
import com.eater.eater.repository.courier.CourierRepository;
import com.eater.eater.repository.restaurantOwner.RestaurantOwnerRepository;
import com.eater.eater.security.JwtService;
import com.eater.eater.utils.mapper.auth.AdminRegistrationMapper;
import com.eater.eater.utils.mapper.auth.ClientRegistrationMapper;
import com.eater.eater.utils.mapper.auth.CourierRegistrationMapper;
import com.eater.eater.utils.mapper.auth.RestaurantOwnerRegistrationMapper;
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
        Courier user = CourierRegistrationMapper.toEntity(input, passwordEncoder);

        userValidationService.validateUser(user.getPhone(), user.getEmail(), user.getPassword());
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
        Admin user = AdminRegistrationMapper.toEntity(input, passwordEncoder);
        userValidationService.validateUser(user.getPhone(), user.getEmail(), user.getPassword());
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

    public LoginResponse clientSignup(ClientRegistrationRequest user) {
        Client client = ClientRegistrationMapper.toEntity(user, passwordEncoder);
        userValidationService.validateUser(user.getPhone(), user.getEmail(), user.getPassword());
        clientRepository.save(client);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getEmail(),
                        user.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwtToken = jwtService.generateToken(client);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);

        return loginResponse;
    }

    public LoginResponse restaurantOwnerSignup(RestaurantOwnerRegistrationRequest user) {
        RestaurantOwner client = RestaurantOwnerRegistrationMapper.toEntity(user, passwordEncoder);
        userValidationService.validateUser(user.getPhone(), user.getEmail(), user.getPassword());
        restaurantOwnerRepository.save(client);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getEmail(),
                        user.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwtToken = jwtService.generateToken(client);
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



}
