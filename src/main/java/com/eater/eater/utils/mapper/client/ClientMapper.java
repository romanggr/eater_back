package com.eater.eater.utils.mapper.client;

import com.eater.eater.dto.client.ClientDTO;
import com.eater.eater.dto.client.UpdateClientRequest;
import com.eater.eater.model.client.Client;
import org.springframework.stereotype.Component;

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
}
