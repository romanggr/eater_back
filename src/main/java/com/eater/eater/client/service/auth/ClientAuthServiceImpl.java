package com.eater.eater.client.service.auth;

import com.eater.eater.DTO.auth.AuthResponse;
import com.eater.eater.DTO.auth.LoginRequest;
import com.eater.eater.DTO.auth.UpdatePasswordRequest;
import com.eater.eater.client.dto.ClientDTO;
import com.eater.eater.client.dto.ClientRegistrationRequest;
import com.eater.eater.client.dto.UpdateClientRequest;
import com.eater.eater.enums.FileCategory;
import com.eater.eater.client.model.Client;
import com.eater.eater.client.repository.ClientRepository;
import com.eater.eater.email.service.EmailConfirmation;
import com.eater.eater.service.S3.S3AvatarService;
import com.eater.eater.security.SecurityUtil;
import com.eater.eater.security.UserValidationService;
import com.eater.eater.client.utils.ClientMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
@RequiredArgsConstructor
public class ClientAuthServiceImpl implements ClientAuthService {
    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserValidationService userValidationService;
    private final SecurityUtil securityUtil;
    private final S3AvatarService s3AvatarService;
    private final EmailConfirmation emailConfirmation;


    public AuthResponse<ClientDTO> signUp(ClientRegistrationRequest input) {
        // validation
        userValidationService.signUpValidation(input.getPhone(), input.getEmail(), input.getPassword());
        emailConfirmation.verifyEmailCode(input.getEmail(), input.getEmailCode());


        // save in db
        Client startUser = ClientMapper.authToEntity(input, passwordEncoder,
                "https://eater-bucket.s3.eu-north-1.amazonaws.com/avatars/default-avatar.webp");
        clientRepository.save(startUser);

        // save avatar in aws
        Client user = addAvatar(input.getAvatar(), startUser);

        // add in context
        securityUtil.addContext(input.getEmail(), input.getPassword());

        // create and return response
        ClientDTO userDTO = ClientMapper.toDTO(user);
        return securityUtil.createAuthResponse(user, userDTO);
    }

    private Client addAvatar(MultipartFile avatar, Client user) {
        String avatarUrl = s3AvatarService.putObjectIntoBucket(user.getId(), avatar, FileCategory.AVATAR);
        user.setAvatarUrl(avatarUrl);
        return clientRepository.save(user);
    }

    public AuthResponse<ClientDTO> login(LoginRequest input) {
        userValidationService.validateEmail(input.getEmail());
        // add in context
        securityUtil.addContext(input.getEmail(), input.getPassword());

        // create and return response
        Client user = clientRepository.findByEmail(input.getEmail()).
                orElseThrow(() -> new EntityNotFoundException("Client not found"));
        ClientDTO userDTO = ClientMapper.toDTO(user);
        return securityUtil.createAuthResponse(user, userDTO);
    }

    public AuthResponse<ClientDTO> update(UpdateClientRequest request) {
        // get current data
        Long currentUserId = SecurityUtil.getCurrentUserId(Client.class);
        Client currentUser = clientRepository.findById(currentUserId)
                .orElseThrow(() -> new EntityNotFoundException("Client not found"));

        // data validation
        userValidationService.updateValidation(request.getPhone(), request.getEmail(), currentUser.getEmail(), request.getPassword(), currentUser.getPassword(), passwordEncoder);

        // update in aws
        Client userWithAvatar = updateAvatar(request, currentUser);

        // update in db
        Client userEntity = ClientMapper.updateRequestToEntity(request, userWithAvatar);
        Client updatedUser = clientRepository.save(userEntity);

        // add in context
        securityUtil.addContext(updatedUser.getEmail(), request.getPassword());

        // create and return response
        ClientDTO userDTO = ClientMapper.toDTO(updatedUser);
        return securityUtil.createAuthResponse(updatedUser, userDTO);
    }

    private Client updateAvatar(UpdateClientRequest request, Client user) {
        if (request.getAvatar() == null || request.getAvatar().isEmpty()) {
            return user;
        }
        String avatarUrl = s3AvatarService.putObjectIntoBucket(user.getId(), request.getAvatar(), FileCategory.AVATAR);
        user.setAvatarUrl(avatarUrl);
        return user;
    }

    public AuthResponse<ClientDTO> updatePassword(UpdatePasswordRequest request) {
        // get current data
        Long currentUserId = SecurityUtil.getCurrentUserId(Client.class);
        Client currentUser = clientRepository.findById(currentUserId)
                .orElseThrow(() -> new EntityNotFoundException("Client not found"));

        // data validation
        userValidationService.updatePasswordValidation(request.getNewPassword(), request.getCurrentPassword(), currentUser.getPassword(), passwordEncoder);

        // add in context
        securityUtil.addContext(currentUser.getEmail(), request.getCurrentPassword());

        // update in db
        currentUser.setPassword(passwordEncoder.encode(request.getNewPassword()));
        Client updatedUser = clientRepository.save(currentUser);

        // create and return response
        ClientDTO userDTO = ClientMapper.toDTO(updatedUser);
        return securityUtil.createAuthResponse(updatedUser, userDTO);
    }

}
