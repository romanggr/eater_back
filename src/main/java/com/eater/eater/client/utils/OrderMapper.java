package com.eater.eater.client.utils;

import com.eater.eater.client.model.Client;
import com.eater.eater.courier.model.Courier;
import com.eater.eater.enums.OrderStatus;
import com.eater.eater.model.order.Orders;
import com.eater.eater.restaurantOwner.model.RestaurantOwner;

import java.time.LocalDateTime;

public class OrderMapper {

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