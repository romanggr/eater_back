package com.eater.eater.service.courier;

import com.eater.eater.dto.courier.*;
import com.eater.eater.enums.CourierStatus;
import com.eater.eater.model.courier.Courier;
import com.eater.eater.model.courier.CourierCoordinates;
import com.eater.eater.repository.courier.CourierCoordinatesRepository;
import com.eater.eater.repository.courier.CourierRepository;
import com.eater.eater.security.SecurityUtil;
import com.eater.eater.utils.mapper.courier.CourierMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CourierService {
    private final CourierRepository courierRepository;
    private final CourierCoordinatesRepository courierCoordinatesRepository;
    @Autowired
    public CourierService(CourierRepository courierRepository, CourierCoordinatesRepository courierCoordinatesRepository) {
        this.courierRepository = courierRepository;
        this.courierCoordinatesRepository = courierCoordinatesRepository;
    }


    //get courier
    public CourierDTO getCourier() {
        Long currentUserId = SecurityUtil.getCurrentUserId(Courier.class);
        Courier courier = courierRepository.findById(currentUserId).orElseThrow(
                () -> new EntityNotFoundException("Courier not found"));

        SecurityUtil.validateUserIsBanned(courier.getCourierStatus());

        return CourierMapper.toDTO(courier);
    }

    // Update coordinates
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

    // update isActive
    public CourierDTO updateIsActive(CourierIsActiveDTO isActiveDTO) {
        Courier currentUser = SecurityUtil.getCurrentUser(Courier.class);

        Courier currentCourier = courierRepository.findById(currentUser.getId())
                .orElseThrow(() -> new EntityNotFoundException("Courier not found"));

        SecurityUtil.validateCourierStatus(currentCourier.getCourierStatus());

        currentCourier.setCourierStatus(isActiveDTO.getIsActive() ? CourierStatus.AVAILABLE : CourierStatus.OFF_DUTY);
        courierRepository.save(currentCourier);

        return CourierMapper.toDTO(currentCourier);
    }

}
