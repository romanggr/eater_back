package com.eater.eater.admin.service.stats;

import com.eater.eater.admin.dto.statistic.StatisticDTO;
import com.eater.eater.client.repository.ClientRepository;
import com.eater.eater.courier.repository.CourierRepository;
import com.eater.eater.model.order.Orders;
import com.eater.eater.repository.order.OrdersRepository;
import com.eater.eater.restaurantOwner.repository.RestaurantOwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatsServiceImpl implements StatsService {
    private final CourierRepository courierRepository;
    private final ClientRepository clientRepository;
    private final RestaurantOwnerRepository restaurantOwnerRepository;
    private final OrdersRepository ordersRepository;

    @Override
    public StatisticDTO getStatistics() {
        List<Orders> orders = ordersRepository.findAll();
        long courierQuantity = courierRepository.count();
        long clientQuantity = clientRepository.count();
        long restaurantQuantity = restaurantOwnerRepository.count();
        long ordersPerDay = (int) orders.stream().filter(o -> o.getFinishedAt() != null && o.getFinishedAt().toLocalDate().equals(LocalDate.now())).count();
        double averageOrderValue = orders.stream().mapToDouble(Orders::getTotalPrice).average().orElse(0.0);
        double appEarningsPerDay = orders.stream().mapToDouble(Orders::getAppEarnings).sum();
        double cashFlowPerDay = orders.stream().mapToDouble(Orders::getTotalPrice).sum();

        return StatisticDTO.builder()
                .courierQuantity(courierQuantity)
                .restaurantQuantity(restaurantQuantity)
                .clientQuantity(clientQuantity)
                .ordersPerDay(ordersPerDay)
                .averageOrderValue(averageOrderValue)
                .appEarningsPerDay(appEarningsPerDay)
                .cashFlowPerDay(cashFlowPerDay)
                .build();
    }
}
