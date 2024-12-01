package com.eater.eater.controller;

import com.eater.eater.dto.client.ClientDTO;
import com.eater.eater.dto.courier.CourierCoordinatesDTO;
import com.eater.eater.dto.courier.CourierRatingDTO;
import com.eater.eater.dto.orders.CreateOrderRequest;
import com.eater.eater.dto.orders.OrderDTOClient;
import com.eater.eater.dto.orders.OrderHistoryClientDTO;
import com.eater.eater.service.client.ClientServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/client")
public class ClientController {
    private final ClientServiceImpl clientServiceImpl;

    public ClientController(ClientServiceImpl clientService) {
        this.clientServiceImpl = clientService;
    }

    @GetMapping("/getClient")
    public ResponseEntity<ClientDTO> getClient() {
        ClientDTO response = clientServiceImpl.getClient();
        return ResponseEntity.ok(response);
    }


    @GetMapping("/getCourierCoordinates/{id}")
    public ResponseEntity<CourierCoordinatesDTO> getClient(@PathVariable Long id) {
        CourierCoordinatesDTO response = clientServiceImpl.getCourierCoordinates(id);
        return ResponseEntity.ok(response);
    }


    @PostMapping("/rateCourier")
    public ResponseEntity<CourierRatingDTO> setRating(@RequestBody CourierRatingDTO courierRatingDTO) {
        CourierRatingDTO response = clientServiceImpl.setCourierRating(courierRatingDTO);
        return ResponseEntity.ok(response);
    }


    @PostMapping("/createOrder")
    public ResponseEntity<OrderDTOClient> createOrder(@RequestBody CreateOrderRequest request) {
        OrderDTOClient response = clientServiceImpl.createOrder(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/confirmOrder/{id}")
    public ResponseEntity<OrderDTOClient> confirmOrder(@PathVariable Long id) {
        OrderDTOClient response = clientServiceImpl.confirmOrder(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getClientOrderHistory")
    public ResponseEntity<List<OrderHistoryClientDTO>> getClientOrderHistory() {
        List<OrderHistoryClientDTO> response = clientServiceImpl.getClientOrderHistory();
        return ResponseEntity.ok(response);
    }

}
