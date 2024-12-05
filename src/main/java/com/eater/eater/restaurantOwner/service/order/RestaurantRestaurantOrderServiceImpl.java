package com.eater.eater.restaurantOwner.service.order;

import com.eater.eater.enums.OrderStatus;
import com.eater.eater.model.order.Orders;
import com.eater.eater.repository.order.OrdersRepository;
import com.eater.eater.restaurantOwner.dto.order.OrderDTORestaurant;
import com.eater.eater.restaurantOwner.dto.order.OrderHistoryRestaurantDTO;
import com.eater.eater.restaurantOwner.model.RestaurantOwner;
import com.eater.eater.restaurantOwner.utils.RestaurantMapper;
import com.eater.eater.security.SecurityUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantRestaurantOrderServiceImpl implements RestaurantOrderService {
    private final OrdersRepository ordersRepository;

    @Override
    public List<OrderDTORestaurant> getNewOrders() {
        Long currentUserId = SecurityUtil.getCurrentUserId(RestaurantOwner.class);

        List<Orders> orders = ordersRepository.findNewOrdersOfRestaurant(currentUserId)
                .orElseThrow(() -> new EntityNotFoundException("Aren't available orders"));

        return orders.stream().map(RestaurantMapper::toOrderDTO).toList();
    }

    @Override
    public OrderDTORestaurant setOrderCooked(Long orderId) {
        Long currentUserId = SecurityUtil.getCurrentUserId(RestaurantOwner.class);

        Orders order = ordersRepository.findOrderOfRestaurant(currentUserId, orderId)
                .orElseThrow(() -> new EntityNotFoundException("It order doesn't exist or it isn't your order"));

        order.setStatus(OrderStatus.APPROVED_BY_RESTAURANT);
        ordersRepository.save(order);

        return RestaurantMapper.toOrderDTO(order);
    }

    @Override
    public List<OrderHistoryRestaurantDTO> getOrdersHistory() {
        Long currentUserId = SecurityUtil.getCurrentUserId(RestaurantOwner.class);

        List<Orders> orders = ordersRepository.findAllOrdersOfRestaurant(currentUserId)
                .orElseThrow(() -> new EntityNotFoundException("Orders not found"));

        return orders.stream()
                .map(RestaurantMapper::toOrderHistoryDTO)
                .toList();
    }
}
