package com.eater.eater.utils;

import com.eater.eater.dto.OrdersDTO;
import com.eater.eater.model.Orders;
import com.eater.eater.model.courier.Courier;
import com.eater.eater.model.Client;

public class OrdersMapper {
//
//    public static OrdersDTO toDTO(Orders orders) {
//        if (orders == null) {
//            return null;
//        }
//        OrdersDTO ordersDTO = new OrdersDTO();
//        ordersDTO.setId(orders.getId());
//        ordersDTO.setPrice(orders.getPrice());
//        ordersDTO.setRestaurantName(orders.getRestaurantName());
//        ordersDTO.setRestaurantAddress(orders.getRestaurantAddress());
//        ordersDTO.setDistance(orders.getDistance());
//        ordersDTO.setStartTime(orders.getStartTime());
//        ordersDTO.setFinishTime(orders.getFinishTime());
//
//        if (orders.getClient() != null) {
//            ordersDTO.setClientId(orders.getClient().getId());
//            ordersDTO.setClientAddress(orders.getClient().getAddress());
//            ordersDTO.setClientName(orders.getClient().getName());
//            ordersDTO.setClientAvatarUrl(orders.getClient().getAvatarUrl());
//            ordersDTO.setClientPhone(orders.getClient().getPhone());
//            ordersDTO.setClientEmail(orders.getClient().getEmail());
//        }
//
//        if (orders.getCourier() != null) {
//            ordersDTO.setCourierId(orders.getCourier().getId());
//            ordersDTO.setCourierName(orders.getCourier().getName());
//            ordersDTO.setCourierEmail(orders.getCourier().getEmail());
//            ordersDTO.setCourierPhone(orders.getCourier().getPhone());
//            ordersDTO.setCourierAvatarUrl(orders.getCourier().getAvatarUrl());
//
//            if (orders.getCourier().getCoordinates() != null) {
//                ordersDTO.setCourierLatitude(orders.getCourier().getCoordinates().getLatitude());
//                ordersDTO.setCourierLongitude(orders.getCourier().getCoordinates().getLongitude());
//                ordersDTO.setCourierCoordinatesLastUpdate(orders.getCourier().getCoordinates().getLastUpdate());
//            }
//        }
//
//        return ordersDTO;
//    }
//
//    public static Orders toEntity(OrdersDTO ordersDTO, Courier courier, Client client) {
//        if (ordersDTO == null) {
//            return null;
//        }
//        Orders orders = new Orders();
//        orders.setId(ordersDTO.getId());
//        orders.setPrice(ordersDTO.getPrice());
//        orders.setRestaurantName(ordersDTO.getRestaurantName());
//        orders.setRestaurantAddress(ordersDTO.getRestaurantAddress());
//        orders.setStartTime(ordersDTO.getStartTime());
//        orders.setFinishTime(ordersDTO.getFinishTime());
//        orders.setDistance(ordersDTO.getDistance());
//
//        if (courier != null) {
//            orders.setCourier(courier);
//        }
//
//        if (client != null) {
//            orders.setClient(client);
//        }
//
//        return orders;
//    }
}
