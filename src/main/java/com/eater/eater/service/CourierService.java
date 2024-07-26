package com.eater.eater.service;

import com.eater.eater.model.courier.Courier;
import com.eater.eater.repository.CourierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourierService {
    private final CourierRepository courierRepository;

    @Autowired
    public CourierService(CourierRepository courierRepository) {
        this.courierRepository = courierRepository;
    }

//    public List<Courier> getAllCourier() {
//        return courierRepository.findAll();
//    }
//
//    public Optional<Courier> getCourier(Long id) {
//        return courierRepository.findById(id);
//    }
//
//    public Courier addCourier(Courier courier) {
//        return courierRepository.save(courier);
//    }
}
