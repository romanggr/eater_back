package com.eater.eater.restaurantOwner.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalTime;

@Data
public class UpdateRestaurantRequest {
    private String name;
    private String description;
    private String address;

    @NumberFormat(pattern = "#.#####")
    private Double latitude;
    @NumberFormat(pattern = "#.#####")
    private Double longitude;

    private MultipartFile image;

    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime isOpenFrom;
    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime isOpenTo;
}
