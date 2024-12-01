package com.eater.eater.service.googleCloud;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DistanceService {

    @Value("${google.api.key}")
    private String apiKey;

    public int getDistance(double courierLat, double courierLon, double clientLat, double clientLon) {
        String url = String.format(
                java.util.Locale.US,
                "https://maps.googleapis.com/maps/api/distancematrix/json?origins=%f,%f&destinations=%f,%f&mode=driving&key=%s",
                courierLat, courierLon, clientLat, clientLon, apiKey);

        RestTemplate restTemplate = new RestTemplate();
        ResponseDTO response = restTemplate.getForObject(url, ResponseDTO.class);

        if (response != null && "OK".equals(response.getStatus())) {
            ResponseDTO.Row firstRow = response.getRows().get(0);
            ResponseDTO.Element firstElement = firstRow.getElements().get(0);
            return firstElement.getDistance().getValue();
        }

        throw new RuntimeException("Failed to fetch distance from Google API");
    }


    public int getDistance(double courierLat, double courierLon, double clientLat, double clientLon, double restaurantLat, double restaurantLon) {
        String url = String.format(
                java.util.Locale.US,
                "https://maps.googleapis.com/maps/api/distancematrix/json?origins=%f,%f&destinations=%f,%f&waypoints=%f,%f&mode=driving&key=%s",
                courierLat, courierLon, clientLat, clientLon, restaurantLat, restaurantLon, apiKey);

        RestTemplate restTemplate = new RestTemplate();
        ResponseDTO response = restTemplate.getForObject(url, ResponseDTO.class);

        if (response != null && "OK".equals(response.getStatus())) {
            ResponseDTO.Row firstRow = response.getRows().get(0);
            ResponseDTO.Element firstElement = firstRow.getElements().get(0);
            return firstElement.getDistance().getValue();
        }

        throw new RuntimeException("Failed to fetch distance from Google API");
    }
}
