package com.eater.eater.service.orders;

import com.eater.eater.enums.CourierStatus;
import com.eater.eater.exception.NoAvailableCouriersException;
import com.eater.eater.model.courier.Courier;
import com.eater.eater.repository.courier.CourierRepository;
import com.eater.eater.service.googleCloud.DistanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrdersServiceImpl implements OrdersService {
    private final CourierRepository courierRepository;
    private final DistanceService distanceService;

    @Override
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
