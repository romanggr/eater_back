package com.eater.eater.controller.courier;

import com.eater.eater.model.courier.Courier;
import com.eater.eater.service.CourierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class CourierController {
    private final CourierService courierService;

    @Autowired
    public CourierController(CourierService courierService) {
        this.courierService = courierService;
    }


    //Get All
//    @GetMapping("/couriers")
//    public List<Courier> getAllProducts() {
//        System.out.println(courierService.getAllCourier());
//        return courierService.getAllCourier();
//    }
//
//    //Get One
//    @GetMapping("/couriers/{id}")
//    public Optional<Courier> getCourier(@PathVariable Long id) {
//        return courierService.getCourier(id);
//    }
//
//    //Post
//    @PostMapping("/courier")
//    public Courier saveProduct(@RequestBody Courier courier) {
//        Courier newCourier = courierService.addCourier(courier);
//        return courier;
//    }
}
