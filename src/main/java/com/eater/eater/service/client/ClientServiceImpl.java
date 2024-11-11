package com.eater.eater.service.client;

import com.eater.eater.dto.client.ClientDTO;
import com.eater.eater.dto.courier.CourierCoordinatesDTO;
import com.eater.eater.dto.courier.CourierRatingDTO;
import com.eater.eater.model.client.Client;
import com.eater.eater.model.courier.Courier;
import com.eater.eater.model.courier.CourierCoordinates;
import com.eater.eater.model.courier.CourierRating;
import com.eater.eater.repository.client.ClientRepository;
import com.eater.eater.repository.courier.CourierRatingRepository;
import com.eater.eater.repository.courier.CourierRepository;
import com.eater.eater.security.SecurityUtil;
import com.eater.eater.utils.mapper.client.ClientMapper;
import com.eater.eater.utils.mapper.courier.CourierMapper;
import com.eater.eater.utils.mapper.courier.CourierRatingMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final CourierRepository courierRepository;
    private final CourierRatingRepository courierRatingRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository, CourierRepository courierRepository, CourierRatingRepository courierRatingRepository) {
        this.clientRepository = clientRepository;
        this.courierRepository = courierRepository;
        this.courierRatingRepository = courierRatingRepository;
    }


    public ClientDTO getClient() {
        Long currentUserId = SecurityUtil.getCurrentUserId(Client.class);
        Client currentClient = clientRepository.findById(currentUserId).orElseThrow(
                () -> new EntityNotFoundException("Client not found"));

        SecurityUtil.validateUserIsBanned(currentClient.getClientStatus());

        return ClientMapper.toDTO(currentClient);
    }


    public CourierCoordinatesDTO getCourierCoordinates(Long courierId) {
        Courier courier = courierRepository.findById(courierId).orElseThrow(
                () -> new EntityNotFoundException("Courier with id: " + courierId + " not found"));
        CourierCoordinates coordinates = courier.getCoordinates();

        return CourierMapper.coordinatesToDTO(coordinates);
    }


    public CourierRatingDTO setCourierRating(CourierRatingDTO courierRatingDTO) {
        if (courierRatingDTO.getRating() > 5 || courierRatingDTO.getRating() < 1) {
            throw new IllegalArgumentException("The rating should be from 1 to 5");
        }

        Courier courier = courierRepository.findById(courierRatingDTO.getCourierId())
                .orElseThrow(() -> new EntityNotFoundException("Courier with id: " + courierRatingDTO.getCourierId() + " not found"));

        Client client = clientRepository.findById(courierRatingDTO.getClientId())
                .orElseThrow(() -> new EntityNotFoundException("Client with id: " + courierRatingDTO.getClientId() + " not found"));

        CourierRating courierRating = CourierRatingMapper.toEntity(courierRatingDTO, courier, client);
        courierRatingRepository.save(courierRating);

        return CourierRatingMapper.toDTO(courierRating);
    }


//todo
// Create order
// Confirm order getting
// Get order
// Get orders history

}
