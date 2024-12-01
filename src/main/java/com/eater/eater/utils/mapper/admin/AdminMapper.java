package com.eater.eater.utils.mapper.admin;

import com.eater.eater.dto.admin.*;
import com.eater.eater.dto.auth.AdminRegistrationRequest;
import com.eater.eater.dto.orders.OrderDTOAdmin;
import com.eater.eater.enums.OrderStatus;
import com.eater.eater.enums.TransportType;
import com.eater.eater.model.admin.Admin;
import com.eater.eater.model.orders.OrderMenu;
import com.eater.eater.model.orders.Orders;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;

public class AdminMapper {
    public static AdminDTO toDTO(Admin admin) {
        if (admin == null) {
            throw new IllegalArgumentException("Parameter cannot be null or empty.");
        }

        AdminDTO adminDTO = new AdminDTO();
        adminDTO.setId(admin.getId());
        adminDTO.setAccepted(admin.isAccepted());
        adminDTO.setName(admin.getName());
        adminDTO.setPhone(admin.getPhone());
        adminDTO.setEmail(admin.getEmail());
        adminDTO.setRole(admin.getRole());

        return adminDTO;
    }

    public static Admin authToEntity(AdminRegistrationRequest input, PasswordEncoder passwordEncoder) {
        Admin user = new Admin();
        user.setName(input.getName());
        user.setEmail(input.getEmail());
        user.setPhone(input.getPhone());
        user.setPassword(passwordEncoder.encode(input.getPassword()));

        return user;
    }

    public static Admin updateRequestToEntity(UpdateAdminRequest request, Admin currentAdmin) {
        if (request == null || currentAdmin == null) {
            throw new IllegalArgumentException("Parameter cannot be null or empty.");
        }

        currentAdmin.setEmail(request.getEmail());
        currentAdmin.setPhone(request.getPhone());
        currentAdmin.setName(request.getName());

        return currentAdmin;
    }

    public static OrderDTOAdmin toOrderDTO(Orders order) {
        OrderDTOAdmin dto = new OrderDTOAdmin();

        dto.setId(order.getId());
        dto.setTotalPrice(order.getTotalPrice());
        dto.setAppEarnings(order.getAppEarnings());
        dto.setCreatedAt(order.getCreatedAt());
        dto.setFinishedAt(order.getFinishedAt());
        dto.setStatus(order.getStatus());
        dto.setDistance(order.getDistance());

        if (order.getCourier() != null) {
            dto.setCourierEarnings(order.getCourierEarnings());
            dto.setCourierName(order.getCourier().getName());
            dto.setCourierEmail(order.getCourier().getEmail());
            dto.setCourierPhone(order.getCourier().getPhone());
            dto.setCourierAvatarUrl(order.getCourier().getAvatarUrl());
            dto.setCourierTransportType(order.getCourier().getTransportType());
            dto.setCourierLatitude(order.getCourier().getCoordinates().getLatitude());
            dto.setCourierLongitude(order.getCourier().getCoordinates().getLongitude());
            dto.setCourierCoordinatesLastUpdate(order.getCourier().getCoordinates().getLastUpdate());
        }

        if (order.getClient() != null) {
            dto.setClientAddress(order.getClient().getAddress());
            dto.setClientName(order.getClient().getName());
            dto.setClientPhone(order.getClient().getPhone());
            dto.setClientEmail(order.getClient().getEmail());
            dto.setClientAvatarUrl(order.getClient().getAvatarUrl());
            dto.setClientLatitude(order.getClient().getLatitude());
            dto.setClientLongitude(order.getClient().getLongitude());
        }

        if (order.getRestaurantOwner() != null) {
            dto.setRestaurantName(order.getRestaurantOwner().getRestaurant().getName());
            dto.setRestaurantPhone(order.getRestaurantOwner().getPhone());
            dto.setRestaurantEmail(order.getRestaurantOwner().getEmail());
            dto.setRestaurantEarnings(order.getRestaurantEarnings());
            dto.setRestaurantAddress(order.getRestaurantOwner().getRestaurant().getAddress());
            dto.setRestaurantLatitude(order.getRestaurantOwner().getRestaurant().getLatitude());
            dto.setRestaurantLongitude(order.getRestaurantOwner().getRestaurant().getLongitude());
            dto.setRestaurantPhotoUrl(order.getRestaurantOwner().getRestaurant().getAvatarUrl());
        }

        dto.setOrderMenus(order.getOrderMenus());

        return dto;
    }

}
