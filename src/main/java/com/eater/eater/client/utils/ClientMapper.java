package com.eater.eater.client.utils;

import com.eater.eater.admin.dto.ClientsForAdminDTO;
import com.eater.eater.client.dto.ClientRegistrationRequest;
import com.eater.eater.client.dto.ClientDTO;
import com.eater.eater.client.dto.UpdateClientRequest;
import com.eater.eater.admin.dto.order.MenuResponse;
import com.eater.eater.client.dto.order.OrderDTOClient;
import com.eater.eater.client.dto.order.OrderHistoryClientDTO;
import com.eater.eater.client.model.Client;
import com.eater.eater.courier.model.Courier;
import com.eater.eater.model.order.OrderMenu;
import com.eater.eater.model.order.Orders;
import com.eater.eater.restaurantOwner.model.RestaurantOwner;
import com.eater.eater.courier.utils.CourierMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.stream.Collectors;

public class ClientMapper {

    public static ClientDTO toDTO(Client client) {
        if (client == null) {
            return null;
        }

        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId(client.getId());
        clientDTO.setName(client.getName());
        clientDTO.setAddress(client.getAddress());
        clientDTO.setLatitude(client.getLatitude());
        clientDTO.setLongitude(client.getLongitude());
        clientDTO.setEmail(client.getEmail());
        clientDTO.setPhone(client.getPhone());
        clientDTO.setAvatarUrl(client.getAvatarUrl());
        clientDTO.setRole(client.getRole());
        clientDTO.setClientStatus(client.getClientStatus());

        return clientDTO;
    }

    public static Client authToEntity(ClientRegistrationRequest input, PasswordEncoder passwordEncoder, String avatarUrl) {
        Client client = new Client();
        client.setName(input.getName());
        client.setAddress(input.getAddress());
        client.setLatitude(input.getLatitude());
        client.setLongitude(input.getLongitude());
        client.setEmail(input.getEmail());
        client.setPhone(input.getPhone());
        client.setAvatarUrl(avatarUrl == null ? "https://eater-bucket.s3.eu-north-1.amazonaws.com/avatars/default-avatar.webp" : avatarUrl);
        client.setPassword(passwordEncoder.encode(input.getPassword()));

        return client;
    }

    public static Client updateRequestToEntity(UpdateClientRequest clientDTO, Client client) {
        if (clientDTO == null) {
            throw new IllegalArgumentException("Parameter cannot be null or empty.");
        }

        client.setName(clientDTO.getName());
        client.setAddress(clientDTO.getAddress());
        client.setLatitude(clientDTO.getLatitude());
        client.setLongitude(clientDTO.getLongitude());
        client.setEmail(clientDTO.getEmail());
        client.setPhone(clientDTO.getPhone());

        return client;
    }

    public static List<ClientsForAdminDTO> allClientToDTO(List<Client> clients) {
        if (clients == null || clients.isEmpty()) {
            throw new IllegalArgumentException("Parameter cannot be null or empty.");
        }

        return clients.stream()
                .map(client -> {
                    ClientsForAdminDTO dto = new ClientsForAdminDTO();
                    dto.setId(client.getId());
                    dto.setName(client.getName());
                    dto.setPhone(client.getPhone());
                    dto.setAvatarUrl(client.getAvatarUrl());
                    dto.setClientStatus(client.getClientStatus());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public static OrderDTOClient orderToDTO(Orders order) {
        if (order == null) {
            throw new IllegalArgumentException("Parameter cannot be null or empty.");
        }
        OrderDTOClient dto = new OrderDTOClient();
        dto.setId(order.getId());
        dto.setTotalPrice(order.getTotalPrice());
        dto.setCreatedAt(order.getCreatedAt());
        dto.setStatus(order.getStatus());

        List<OrderMenu> orderMenus = order.getOrderMenus();
        dto.setOrderMenus(orderMenus.stream().map(ClientMapper::toMenuDTO).toList());

        RestaurantOwner restaurantOwner = order.getRestaurantOwner();
        if (restaurantOwner != null) {
            dto.setRestaurantName(restaurantOwner.getRestaurant().getName());
            dto.setRestaurantPhone(restaurantOwner.getPhone());
            dto.setRestaurantEmail(restaurantOwner.getEmail());
        }

        Courier courier = order.getCourier();
        if (courier != null) {
            dto.setCourierName(courier.getName());
            dto.setCourierEmail(courier.getEmail());
            dto.setCourierPhone(courier.getPhone());
            dto.setCourierAvatarUrl(courier.getAvatarUrl());
            dto.setCourierTransportType(courier.getTransportType());

            if (courier.getCoordinates() != null) {
                dto.setCourierCoordinatesDTO(CourierMapper.coordinatesToDTO(courier.getCoordinates()));
            }
        }

        return dto;
    }

    public static OrderHistoryClientDTO toOrderHistoryDTO(Orders order) {
        OrderHistoryClientDTO dto = new OrderHistoryClientDTO();

        dto.setId(order.getId());
        dto.setTotalPrice(order.getTotalPrice());
        dto.setCreatedAt(order.getCreatedAt());
        dto.setFinishedAt(order.getFinishedAt());

        if (order.getRestaurantOwner() != null) {
            dto.setRestaurantName(order.getRestaurantOwner().getRestaurant().getName());
            dto.setRestaurantAddress(order.getRestaurantOwner().getRestaurant().getAddress());
        }

        if (order.getOrderMenus() != null) {
            dto.setOrderMenu(order.getOrderMenus());
        }

        return dto;
    }

    public static MenuResponse toMenuDTO(OrderMenu menu) {
        if (menu == null) {
            throw new IllegalArgumentException("Parameter cannot be null or empty.");
        }

        MenuResponse dto = new MenuResponse();

        dto.setDishName(menu.getDish().getName());
        dto.setDishPrice(menu.getDish().getPrice());
        dto.setQuantity(menu.getDishQuantity());
        dto.setDishImageUrl(menu.getDish().getImageUrl());

        return dto;
    }
}
