package com.eater.eater.utils.mapper.client;

import com.eater.eater.dto.admin.ClientsForAdminDTO;
import com.eater.eater.dto.client.ClientDTO;
import com.eater.eater.dto.client.UpdateClientRequest;
import com.eater.eater.model.client.Client;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClientMapper {

    public ClientDTO toDTO(Client client) {
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

    public Client toEntity(UpdateClientRequest clientDTO, Client client) {
        if (clientDTO == null) {
            return null;
        }

        client.setName(clientDTO.getName());
        client.setAddress(clientDTO.getAddress());
        client.setLatitude(clientDTO.getLatitude());
        client.setLongitude(clientDTO.getLongitude());
        client.setEmail(clientDTO.getEmail());
        client.setPhone(clientDTO.getPhone());
        client.setAvatarUrl(clientDTO.getAvatarUrl());

        return client;
    }

    public List<ClientsForAdminDTO> allClientToDTO(List<Client> clients) {
        if (clients == null || clients.isEmpty()) {
            return new ArrayList<>();
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
