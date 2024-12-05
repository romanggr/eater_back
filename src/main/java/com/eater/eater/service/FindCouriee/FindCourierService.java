package com.eater.eater.service.FindCouriee;

import com.eater.eater.courier.model.Courier;
import com.eater.eater.courier.repository.CourierRepository;
import com.eater.eater.enums.CourierStatus;
import com.eater.eater.exception.NoAvailableCouriersException;
import com.eater.eater.service.googleCloud.DistanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindCourierService {
    private final CourierRepository courierRepository;
    private final DistanceService distanceService;


    public Courier findCourier(double restaurantLatitude, double restaurantLongitude) {
        List<Courier> couriers = courierRepository.findAvailableCouriers(CourierStatus.AVAILABLE);
        Courier nearestCourier = null;
        double nearestDistance = Double.MAX_VALUE;

        for (Courier courier : couriers) {
            double courierLatitude = courier.getCoordinates().getLatitude();
            double courierLongitude = courier.getCoordinates().getLongitude();
            double distance = distanceService.getDistance(restaurantLatitude, restaurantLongitude, courierLatitude, courierLongitude);

            if (distance < nearestDistance) {
                nearestDistance = distance;
                nearestCourier = courier;
            }
        }

        if (nearestCourier == null) throw new NoAvailableCouriersException("All couriers can't take your order");

        nearestCourier.setCourierStatus(CourierStatus.ON_DELIVERY);
        return nearestCourier;
    }

}
