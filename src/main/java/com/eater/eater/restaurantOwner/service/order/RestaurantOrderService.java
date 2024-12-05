package com.eater.eater.restaurantOwner.service.order;

import com.eater.eater.restaurantOwner.dto.order.OrderDTORestaurant;
import com.eater.eater.restaurantOwner.dto.order.OrderHistoryRestaurantDTO;

import java.util.List;

public interface RestaurantOrderService {
    List<OrderDTORestaurant> getNewOrders();

    OrderDTORestaurant setOrderCooked(Long id);

    List<OrderHistoryRestaurantDTO> getOrdersHistory();
}
