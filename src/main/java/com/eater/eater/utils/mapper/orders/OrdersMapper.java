package com.eater.eater.utils.mapper.orders;

import com.eater.eater.enums.OrderStatus;
import com.eater.eater.model.client.Client;
import com.eater.eater.model.courier.Courier;
import com.eater.eater.model.orders.OrderMenu;
import com.eater.eater.model.orders.Orders;
import com.eater.eater.model.restaurantOwner.RestaurantOwner;

import java.time.LocalDateTime;
import java.util.List;

public class OrdersMapper {

    public static Orders toEntity(Client client, Courier courier, RestaurantOwner restaurantOwner, int distance) {
        double courierEarnings = distance * 1.2 + 3;
        double appEarnings =  courierEarnings * 20 / 100;

        Orders order = new Orders();
        order.setClient(client);
        order.setCourier(courier);
        order.setRestaurantOwner(restaurantOwner);

        order.setStatus(OrderStatus.CREATED);
        order.setCreatedAt(LocalDateTime.now());
        order.setDistance(distance);
        order.setCourierEarnings(courierEarnings);
        order.setAppEarnings(appEarnings);

        return order;
    }
}
