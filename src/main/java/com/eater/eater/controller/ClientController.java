package com.eater.eater.controller;

import com.eater.eater.model.Client;
import com.eater.eater.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ClientController {
    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

//    @GetMapping("/clients")
//    public List<Client> getClients(){
//        return clientService.getAllUsers();
//    }
//
//    @PostMapping("/client")
//    public Client addClient(@RequestBody Client client){
//        return clientService.addClient(client);
//    }

}
