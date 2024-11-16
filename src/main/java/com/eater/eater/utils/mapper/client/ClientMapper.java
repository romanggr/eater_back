package com.eater.eater.utils.mapper.client;

import com.eater.eater.dto.admin.ClientsForAdminDTO;
import com.eater.eater.dto.auth.ClientRegistrationRequest;
import com.eater.eater.dto.client.ClientDTO;
import com.eater.eater.dto.client.UpdateClientRequest;
import com.eater.eater.model.client.Client;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.stream.Collectors;

public class ClientMapper {

    public static ClientDTO toDTO(Client client) {
        if (client == null) {
            return null;
        }

        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId(client.getId());
        clientDTO.setName(client.getName());
        clientDTO.setAddress(client.getAddress());
        clientDTO.setLatitude(client.getLatitude());
        clientDTO.setLongitude(client.getLongitude());
        clientDTO.setEmail(client.getEmail());
        clientDTO.setPhone(client.getPhone());
        clientDTO.setAvatarUrl(client.getAvatarUrl());
        clientDTO.setRole(client.getRole());
        clientDTO.setClientStatus(client.getClientStatus());

        return clientDTO;
    }

    public static Client authToEntity(ClientRegistrationRequest input, PasswordEncoder passwordEncoder, String avatarUrl) {
        Client client = new Client();
        client.setName(input.getName());
        client.setAddress(input.getAddress());
        client.setLatitude(input.getLatitude());
        client.setLongitude(input.getLongitude());
        client.setEmail(input.getEmail());
        client.setPhone(input.getPhone());
        client.setAvatarUrl(avatarUrl == null ? "https://eater-bucket.s3.eu-north-1.amazonaws.com/avatars/default-avatar.webp" : avatarUrl);
        client.setPassword(passwordEncoder.encode(input.getPassword()));

        return client;
    }

    public static Client updateRequestToEntity(UpdateClientRequest clientDTO, Client client) {
        if (clientDTO == null) {
            throw new IllegalArgumentException("Parameter cannot be null or empty.");
        }

        client.setName(clientDTO.getName());
        client.setAddress(clientDTO.getAddress());
        client.setLatitude(clientDTO.getLatitude());
        client.setLongitude(clientDTO.getLongitude());
        client.setEmail(clientDTO.getEmail());
        client.setPhone(clientDTO.getPhone());

        return client;
    }

    public static List<ClientsForAdminDTO> allClientToDTO(List<Client> clients) {
        if (clients == null || clients.isEmpty()) {
            throw new IllegalArgumentException("Parameter cannot be null or empty.");
        }

        return clients.stream()
                .map(client -> {
                    ClientsForAdminDTO dto = new ClientsForAdminDTO();
                    dto.setId(client.getId());
                    dto.setName(client.getName());
                    dto.setPhone(client.getPhone());
                    dto.setAvatarUrl(client.getAvatarUrl());
                    dto.setClientStatus(client.getClientStatus());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
