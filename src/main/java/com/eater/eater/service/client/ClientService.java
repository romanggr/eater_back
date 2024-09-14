package com.eater.eater.service.client;

import com.eater.eater.dto.client.ClientDTO;
import com.eater.eater.dto.client.UpdateClientRequest;
import com.eater.eater.dto.courier.CourierCoordinatesDTO;
import com.eater.eater.dto.courier.CourierRatingDTO;
import com.eater.eater.dto.auth.UpdatePasswordRequest;
import com.eater.eater.enums.ClientStatus;
import com.eater.eater.model.client.Client;
import com.eater.eater.model.courier.Courier;
import com.eater.eater.model.courier.CourierCoordinates;
import com.eater.eater.model.courier.CourierRating;
import com.eater.eater.repository.client.ClientRepository;
import com.eater.eater.repository.courier.CourierCoordinatesRepository;
import com.eater.eater.repository.courier.CourierRatingRepository;
import com.eater.eater.repository.courier.CourierRepository;
import com.eater.eater.security.SecurityUtil;
import com.eater.eater.service.auth.UserValidationService;
import com.eater.eater.utils.mapper.client.ClientMapper;
import com.eater.eater.utils.mapper.courier.CourierMapper;
import com.eater.eater.utils.mapper.courier.CourierRatingMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    private final ClientMapper clientMapper;
    private final CourierRatingMapper courierRatingMapper;
    private final ClientRepository clientRepository;
    private final CourierRepository courierRepository;
    private final CourierRatingRepository courierRatingRepository;
    private final UserValidationService userValidationService;
    private final CourierMapper courierMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ClientService(ClientMapper clientMapper, CourierRatingMapper courierRatingMapper, ClientRepository clientRepository, CourierRepository courierRepository, CourierCoordinatesRepository courierCoordinatesRepository, CourierRatingRepository courierRatingRepository, UserValidationService userValidationService, CourierMapper courierMapper, PasswordEncoder passwordEncoder) {
        this.clientMapper = clientMapper;
        this.courierRatingMapper = courierRatingMapper;
        this.clientRepository = clientRepository;
        this.courierRepository = courierRepository;
        this.courierRatingRepository = courierRatingRepository;
        this.userValidationService = userValidationService;
        this.courierMapper = courierMapper;
        this.passwordEncoder = passwordEncoder;
    }


    //  Get client data
    public ClientDTO getClient() {
        Long currentUserId = SecurityUtil.getCurrentUserId(Client.class);
        Client currentClient = clientRepository.findById(currentUserId).orElseThrow(
                () -> new EntityNotFoundException("Client not found"));

        SecurityUtil.validateUserIsBanned(currentClient.getClientStatus());

        return clientMapper.toDTO(currentClient);
    }

    // Get courier coordinates
    public CourierCoordinatesDTO getCourierCoordinates(Long courierId) {
        Courier courier = courierRepository.findById(courierId).orElseThrow(
                () -> new EntityNotFoundException("Courier with id: " + courierId + " not found"));
        CourierCoordinates coordinates = courier.getCoordinates();

        return courierMapper.coordinatesToDTO(coordinates);
    }

    // Give courier rating
    public CourierRatingDTO setCourierRating(CourierRatingDTO courierRatingDTO) {
        if (courierRatingDTO.getRating() > 5 || courierRatingDTO.getRating() < 1) {
            throw new IllegalArgumentException("The rating should be from 1 to 5");
        }

        Courier courier = courierRepository.findById(courierRatingDTO.getCourierId())
                .orElseThrow(() -> new EntityNotFoundException("Courier with id: " + courierRatingDTO.getCourierId() + " not found"));

        Client client = clientRepository.findById(courierRatingDTO.getClientId())
                .orElseThrow(() -> new EntityNotFoundException("Client with id: " + courierRatingDTO.getClientId() + " not found"));

        CourierRating courierRating = courierRatingMapper.toEntity(courierRatingDTO, courier, client);
        courierRatingRepository.save(courierRating);

        return courierRatingMapper.toDTO(courierRating);
    }


//todo
// Create order
// Confirm order getting
// Get order
// Get orders history

}
