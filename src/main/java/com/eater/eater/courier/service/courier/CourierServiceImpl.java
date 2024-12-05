package com.eater.eater.courier.service.courier;

import com.eater.eater.courier.dto.CourierCoordinatesDTO;
import com.eater.eater.courier.dto.CourierDTO;
import com.eater.eater.courier.dto.CourierIsActiveDTO;
import com.eater.eater.courier.utils.StatusValidation;
import com.eater.eater.enums.CourierStatus;
import com.eater.eater.courier.model.Courier;
import com.eater.eater.courier.model.CourierCoordinates;
import com.eater.eater.courier.repository.CourierCoordinatesRepository;
import com.eater.eater.courier.repository.CourierRepository;
import com.eater.eater.courier.utils.CourierMapper;
import com.eater.eater.security.SecurityUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CourierServiceImpl implements CourierService {
    private final CourierRepository courierRepository;
    private final CourierCoordinatesRepository courierCoordinatesRepository;


    public CourierDTO updateCourierCoordinates(CourierCoordinatesDTO courierCoordinatesDTO) {
        Courier currentUser = SecurityUtil.getCurrentUser(Courier.class);
        Courier currentCourier = courierRepository.findById(currentUser.getId())
                .orElseThrow(() -> new EntityNotFoundException("Courier not found"));
        CourierCoordinates coordinates = currentCourier.getCoordinates();

        if (coordinates == null) {
            throw new EntityNotFoundException("Coordinates not found for the current courier");
        }

        courierCoordinatesRepository.save(CourierMapper.coordinatesToEntity(coordinates, courierCoordinatesDTO));
        return CourierMapper.toDTO(currentCourier);
    }


    public CourierDTO updateIsActive(CourierIsActiveDTO isActiveDTO) {
        Courier currentUser = SecurityUtil.getCurrentUser(Courier.class);

        Courier currentCourier = courierRepository.findById(currentUser.getId())
                .orElseThrow(() -> new EntityNotFoundException("Courier not found"));

        StatusValidation.validateCourierStatus(currentCourier.getCourierStatus());

        currentCourier.setCourierStatus(isActiveDTO.getIsActive() ? CourierStatus.AVAILABLE : CourierStatus.OFF_DUTY);
        courierRepository.save(currentCourier);

        return CourierMapper.toDTO(currentCourier);
    }



}
