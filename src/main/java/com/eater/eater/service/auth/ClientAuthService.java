package com.eater.eater.service.auth;

import com.eater.eater.dto.auth.*;
import com.eater.eater.dto.client.ClientDTO;
import com.eater.eater.dto.client.UpdateClientRequest;
import com.eater.eater.enums.Role;
import com.eater.eater.model.client.Client;
import com.eater.eater.repository.client.ClientRepository;
import com.eater.eater.security.SecurityUtil;
import com.eater.eater.service.S3.S3ServiceImpl;
import com.eater.eater.utils.mapper.client.ClientMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class ClientAuthService {
    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserValidationService userValidationService;
    private final AuthUtilityService authUtilityService;
    private final S3ServiceImpl s3ServiceImpl;


    @Autowired
    public ClientAuthService(ClientRepository clientRepository, PasswordEncoder passwordEncoder, UserValidationService userValidationService, AuthUtilityService authUtilityService, S3ServiceImpl s3ServiceImpl) {
        this.clientRepository = clientRepository;
        this.passwordEncoder = passwordEncoder;
        this.userValidationService = userValidationService;
        this.authUtilityService = authUtilityService;
        this.s3ServiceImpl = s3ServiceImpl;
    }

    public AuthResponse<ClientDTO> signUp(ClientRegistrationRequest input) {
        // validation
        userValidationService.signUpValidation(input.getPhone(), input.getEmail(), input.getPassword(), Role.CLIENT, input.getAvatar());

        // save in db
        Client user = ClientMapper.authToEntity(input, passwordEncoder, null);
        clientRepository.save(user);

        // save avatar in aws
        addAvatar(input.getAvatar(), user);

        // add in context
        authUtilityService.addContext(input.getEmail(), input.getPassword());

        // create and return response
        ClientDTO userDTO = ClientMapper.toDTO(user);
        return authUtilityService.createAuthResponse(user, userDTO);
    }

    private void addAvatar(MultipartFile avatar, Client user) {
        String avatarUrl = s3ServiceImpl.putObjectIntoBucket(user.getId(), avatar);
        user.setAvatarUrl(avatarUrl);
        clientRepository.save(user);
    }

    public AuthResponse<ClientDTO> login(LoginRequest input) {
        userValidationService.validateEmail(input.getEmail());
        // add in context
        authUtilityService.addContext(input.getEmail(), input.getPassword());

        // create and return response
        Client user = clientRepository.findByEmail(input.getEmail()).
                orElseThrow(() -> new EntityNotFoundException("Client not found"));
        ClientDTO userDTO = ClientMapper.toDTO(user);
        return authUtilityService.createAuthResponse(user, userDTO);
    }

    public AuthResponse<ClientDTO> update(UpdateClientRequest request) {
        // get current data
        Long currentUserId = SecurityUtil.getCurrentUserId(Client.class);
        Client currentUser = clientRepository.findById(currentUserId)
                .orElseThrow(() -> new EntityNotFoundException("Client not found"));

        // data validation
        userValidationService.updateValidation(request.getPhone(), request.getEmail(), currentUser.getEmail(), Role.CLIENT, request.getPassword(), currentUser.getPassword(), passwordEncoder);

        // update in db
        Client userEntity = ClientMapper.updateRequestToEntity(request, currentUser);
        Client updatedUser = clientRepository.save(userEntity);

        // add in context
        authUtilityService.addContext(updatedUser.getEmail(), request.getPassword());

        // create and return response
        ClientDTO userDTO = ClientMapper.toDTO(updatedUser);
        return authUtilityService.createAuthResponse(updatedUser, userDTO);
    }

    public AuthResponse<ClientDTO> updatePassword(UpdatePasswordRequest request) {
        // get current data
        Long currentUserId = SecurityUtil.getCurrentUserId(Client.class);
        Client currentUser = clientRepository.findById(currentUserId)
                .orElseThrow(() -> new EntityNotFoundException("Client not found"));

        // data validation
        userValidationService.updatePasswordValidation(request.getNewPassword(), request.getCurrentPassword(), currentUser.getPassword(), passwordEncoder);

        // add in context
        authUtilityService.addContext(currentUser.getEmail(), request.getCurrentPassword());

        // update in db
        currentUser.setPassword(passwordEncoder.encode(request.getNewPassword()));
        Client updatedUser = clientRepository.save(currentUser);

        // create and return response
        ClientDTO userDTO = ClientMapper.toDTO(updatedUser);
        return authUtilityService.createAuthResponse(updatedUser, userDTO);
    }

}
