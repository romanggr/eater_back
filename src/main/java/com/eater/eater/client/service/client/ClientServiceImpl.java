package com.eater.eater.client.service.client;

import com.eater.eater.courier.dto.CourierCoordinatesDTO;
import com.eater.eater.courier.dto.CourierRatingDTO;
import com.eater.eater.client.model.Client;
import com.eater.eater.courier.model.Courier;
import com.eater.eater.courier.model.CourierCoordinates;
import com.eater.eater.courier.model.CourierRating;
import com.eater.eater.client.repository.ClientRepository;
import com.eater.eater.courier.repository.CourierRatingRepository;
import com.eater.eater.courier.repository.CourierRepository;
import com.eater.eater.courier.utils.CourierMapper;
import com.eater.eater.courier.utils.CourierRatingMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final CourierRepository courierRepository;
    private final CourierRatingRepository courierRatingRepository;


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

}
