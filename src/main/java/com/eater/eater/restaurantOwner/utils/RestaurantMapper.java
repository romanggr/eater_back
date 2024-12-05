package com.eater.eater.restaurantOwner.utils;

import com.eater.eater.restaurantOwner.dto.CreateRestaurantRequest;
import com.eater.eater.restaurantOwner.dto.RestaurantDTO;
import com.eater.eater.restaurantOwner.dto.UpdateRestaurantRequest;
import com.eater.eater.model.order.Orders;
import com.eater.eater.restaurantOwner.dto.order.OrderDTORestaurant;
import com.eater.eater.restaurantOwner.dto.order.OrderHistoryRestaurantDTO;
import com.eater.eater.restaurantOwner.model.Restaurant;
import com.eater.eater.restaurantOwner.model.RestaurantOwner;


public class RestaurantMapper {

    public static RestaurantDTO toDTO(Restaurant restaurant) {
        if (restaurant == null) {
            throw new IllegalArgumentException("Parameter cannot be null or empty.");
        }

        RestaurantDTO restaurantDTO = new RestaurantDTO();
        restaurantDTO.setName(restaurant.getName());
        restaurantDTO.setDescription(restaurant.getDescription());
        restaurantDTO.setAddress(restaurant.getAddress());
        restaurantDTO.setLatitude(restaurant.getLatitude());
        restaurantDTO.setLongitude(restaurant.getLongitude());
        restaurantDTO.setIsOpenFrom(restaurant.getIsOpenFrom());
        restaurantDTO.setIsOpenTo(restaurant.getIsOpenTo());
        restaurantDTO.setAvatarUrl(restaurant.getAvatarUrl());

        return restaurantDTO;
    }

    public static Restaurant updateRequestToEntity(UpdateRestaurantRequest request, Restaurant restaurant) {
        if (request == null || restaurant == null) {
            throw new IllegalArgumentException("Parameter cannot be null or empty.");
        }

        restaurant.setAddress(request.getAddress());
        restaurant.setName(request.getName());
        restaurant.setDescription(request.getDescription());
        restaurant.setIsOpenFrom(request.getIsOpenFrom());
        restaurant.setIsOpenTo(request.getIsOpenTo());
        restaurant.setLatitude(request.getLatitude());
        restaurant.setLongitude(request.getLongitude());

        return restaurant;
    }

    public static Restaurant restaurantToEntity(CreateRestaurantRequest request, RestaurantOwner restaurantOwner, String defaultUrl) {
        if (request == null) {
            throw new IllegalArgumentException("Parameter cannot be null or empty.");
        }

        Restaurant restaurant = new Restaurant();
        restaurant.setName(request.getName());
        restaurant.setDescription(request.getDescription());
        restaurant.setIsOpenFrom(request.getIsOpenFrom());
        restaurant.setIsOpenTo(request.getIsOpenTo());
        restaurant.setLatitude(request.getLatitude());
        restaurant.setLongitude(request.getLongitude());
        restaurant.setAddress(request.getAddress());
        restaurant.setAvatarUrl(defaultUrl);
        restaurant.setRestaurantOwner(restaurantOwner);

        return restaurant;
    }

    public static OrderDTORestaurant toOrderDTO(Orders order) {
        OrderDTORestaurant dto = new OrderDTORestaurant();
        dto.setId(order.getId());
        dto.setCreatedAt(order.getCreatedAt());
        dto.setRestaurantEarnings(order.getRestaurantEarnings());

        if (order.getOrderMenus() != null) {
            dto.setOrderMenus(order.getOrderMenus());
        }

        if (order.getCourier() != null) {
            dto.setCourierName(order.getCourier().getName());
            dto.setCourierAvatarUrl(order.getCourier().getAvatarUrl());
            dto.setCourierPhone(order.getCourier().getPhone());
            dto.setCourierTransportType(order.getCourier().getTransportType());
        }

        return dto;
    }

    public static OrderHistoryRestaurantDTO toOrderHistoryDTO(Orders order) {
        OrderHistoryRestaurantDTO dto = new OrderHistoryRestaurantDTO();
        dto.setId(order.getId());
        dto.setCreatedAt(order.getCreatedAt());
        dto.setRestaurantEarnings(order.getRestaurantEarnings());

        if (order.getOrderMenus() != null) {
            dto.setOrderMenus(order.getOrderMenus());
        }

        return dto;
    }
}
