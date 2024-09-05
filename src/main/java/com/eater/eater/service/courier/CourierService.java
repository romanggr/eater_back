package com.eater.eater.service.courier;

import com.eater.eater.dto.courier.*;
import com.eater.eater.model.courier.Courier;
import com.eater.eater.model.courier.CourierCoordinates;
import com.eater.eater.repository.courier.CourierCoordinatesRepository;
import com.eater.eater.repository.courier.CourierRepository;
import com.eater.eater.security.SecurityUtil;
import com.eater.eater.service.auth.UserValidationService;
import com.eater.eater.utils.mapper.courier.CourierMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class CourierService {
    private final CourierRepository courierRepository;
    private final CourierCoordinatesRepository courierCoordinatesRepository;
    private final CourierMapper courierMapper;
    private final UserValidationService userValidationService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CourierService(CourierRepository courierRepository, CourierCoordinatesRepository courierCoordinatesRepository, CourierMapper courierMapper, UserValidationService userValidationService, PasswordEncoder passwordEncoder) {
        this.courierRepository = courierRepository;
        this.courierCoordinatesRepository = courierCoordinatesRepository;
        this.courierMapper = courierMapper;
        this.userValidationService = userValidationService;
        this.passwordEncoder = passwordEncoder;
    }


    //get courier
    public CourierDTO getCourier() {
        Courier currentUser = SecurityUtil.getCurrentUser(Courier.class);

        return courierMapper.toDTO(currentUser);
    }

    //update courier
    public CourierDTO updateCourier(UpdateCourierRequest updateCourierRequest) {
        Long currentUserId = SecurityUtil.getCurrentUserId(Courier.class);

        Courier currentCourier = courierRepository.findById(currentUserId).orElseThrow(
                () -> new EntityNotFoundException("Courier not found"));

        userValidationService.validateUser(updateCourierRequest.getEmail(), updateCourierRequest.getPhone());
        Courier courier = courierRepository.save(courierMapper.updateRequestToEntity(updateCourierRequest, currentCourier));

        return courierMapper.toDTO(courier);
    }

    // update courier password
    public CourierDTO updateCourierPassword(UpdateCourierPasswordRequest request) {
        Long currentUserId = SecurityUtil.getCurrentUserId(Courier.class);
        Courier currentCourier = courierRepository.findById(currentUserId).orElseThrow(
                () -> new EntityNotFoundException("Courier not found"));

        userValidationService.validatePassword(request.getPassword());
        currentCourier.setPassword(passwordEncoder.encode(request.getPassword()));
        Courier courier = courierRepository.save(currentCourier);

        return courierMapper.toDTO(courier);
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

        courierCoordinatesRepository.save(courierMapper.coordinatesToEntity(coordinates, courierCoordinatesDTO));

        return courierMapper.toDTO(currentCourier);
    }

    // update isActive
    public CourierDTO updateIsActive(CourierIsActiveDTO isActiveDTO) {
        Courier currentUser = SecurityUtil.getCurrentUser(Courier.class);

        Courier currentCourier = courierRepository.findById(currentUser.getId())
                .orElseThrow(() -> new EntityNotFoundException("Courier not found"));

        currentCourier.setActive(isActiveDTO.getIsActive());
        courierRepository.save(currentCourier);

        return courierMapper.toDTO(currentCourier);
    }
}
