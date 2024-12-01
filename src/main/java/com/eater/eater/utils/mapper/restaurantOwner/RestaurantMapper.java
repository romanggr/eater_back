package com.eater.eater.utils.mapper.restaurantOwner;

import com.eater.eater.dto.orders.OrderDTORestaurant;
import com.eater.eater.dto.orders.OrderHistoryCourierDTO;
import com.eater.eater.dto.orders.OrderHistoryRestaurantDTO;
import com.eater.eater.dto.restaurantOwner.CreateRestaurantRequest;
import com.eater.eater.dto.restaurantOwner.RestaurantDTO;
import com.eater.eater.dto.restaurantOwner.UpdateRestaurantRequest;
import com.eater.eater.model.orders.Orders;
import com.eater.eater.model.restaurantOwner.Restaurant;
import com.eater.eater.model.restaurantOwner.RestaurantOwner;


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

    public static Restaurant toEntity(RestaurantDTO restaurantDTO, RestaurantOwner restaurantOwner) {
        if (restaurantDTO == null) {
            throw new IllegalArgumentException("Parameter cannot be null or empty.");
        }

        Restaurant restaurant = new Restaurant();
        restaurant.setName(restaurantDTO.getName());
        restaurant.setDescription(restaurantDTO.getDescription());
        restaurant.setIsOpenFrom(restaurantDTO.getIsOpenFrom());
        restaurant.setIsOpenTo(restaurantDTO.getIsOpenTo());
        restaurant.setLatitude(restaurantDTO.getLatitude());
        restaurant.setLongitude(restaurantDTO.getLongitude());
        restaurant.setAddress(restaurantDTO.getAddress());
        restaurant.setAvatarUrl(restaurantDTO.getAvatarUrl());
        restaurant.setRestaurantOwner(restaurantOwner);

        return restaurant;
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
