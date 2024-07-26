package com.eater.eater.service;

import com.eater.eater.model.Client;
import com.eater.eater.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

//    public List<Client> getAllUsers() {
//        return clientRepository.findAll();
//    }
//
//    public Client addClient(Client client) {
//        return clientRepository.save(client);
//    }
}
