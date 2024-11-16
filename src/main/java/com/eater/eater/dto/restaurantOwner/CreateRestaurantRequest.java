package com.eater.eater.dto.restaurantOwner;

import lombok.Data;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalTime;

@Data
public class CreateRestaurantRequest {
    private String name;
    private String description;
    private String address;

    @NumberFormat(pattern = "#.#####")
    private Double latitude;
    @NumberFormat(pattern = "#.#####")
    private Double longitude;

    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime isOpenFrom;
    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime isOpenTo;

    private MultipartFile image;
}
