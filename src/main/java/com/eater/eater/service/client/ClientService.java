package com.eater.eater.service.client;

import com.eater.eater.dto.client.ClientDTO;
import com.eater.eater.dto.courier.CourierCoordinatesDTO;
import com.eater.eater.dto.courier.CourierRatingDTO;
import com.eater.eater.dto.orders.CreateOrderRequest;
import com.eater.eater.dto.orders.OrderDTOClient;
import com.eater.eater.dto.orders.OrderHistoryClientDTO;
import com.eater.eater.model.client.Client;
import com.eater.eater.model.courier.Courier;
import com.eater.eater.model.orders.OrderMenu;
import com.eater.eater.model.orders.Orders;
import com.eater.eater.model.restaurantOwner.RestaurantOwner;

import java.util.List;


public interface ClientService {

    ClientDTO getClient();

    CourierCoordinatesDTO getCourierCoordinates(Long courierId);

    CourierRatingDTO setCourierRating(CourierRatingDTO courierRatingDTO);

    OrderDTOClient createOrder(CreateOrderRequest request);

    OrderDTOClient confirmOrder(Long id);

    List<OrderHistoryClientDTO> getClientOrderHistory();
}
