package com.eater.eater.service;

import com.eater.eater.dto.OrdersDTO;
import com.eater.eater.model.Client;
import com.eater.eater.repository.ClientRepository;
import com.eater.eater.utils.OrdersMapper;
import com.eater.eater.model.courier.Courier;
import com.eater.eater.model.Orders;
import com.eater.eater.repository.CourierRepository;
import com.eater.eater.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrdersService {
    private final OrdersRepository ordersRepository;
    private final CourierRepository courierRepository;
    private final ClientRepository clientRepository;

    @Autowired
    public OrdersService(OrdersRepository ordersRepository, CourierRepository courierRepository, ClientRepository clientRepository) {
        this.ordersRepository = ordersRepository;
        this.courierRepository = courierRepository;
        this.clientRepository = clientRepository;
    }
//
//    public List<OrdersDTO> getAllOrders() {
//        return ordersRepository.findAll().stream()
//                .map(OrdersMapper::toDTO)
//                .collect(Collectors.toList());
//    }
//
//    public Optional<OrdersDTO> getOrder(Long id) {
//        return ordersRepository.findById(id).map(OrdersMapper::toDTO);
//    }
//
//    public Orders addOrder(OrdersDTO ordersDTO) {
//        Optional<Courier> courierOptional = courierRepository.findById(ordersDTO.getCourierId());
//        Optional<Client> clientOptional = clientRepository.findById(ordersDTO.getClientId());
//
//        if (courierOptional.isEmpty() || clientOptional.isEmpty()) {
//            throw new IllegalArgumentException("Invalid Courier or Client ID");
//        }
//
//        Courier courier = courierOptional.get();
//        Client client = clientOptional.get();
//
//        Orders orders = OrdersMapper.toEntity(ordersDTO, courier, client);
//        System.out.println(orders.toString());
//        return ordersRepository.save(orders);
//    }

}
