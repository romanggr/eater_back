package com.eater.eater.client.service.order;

import com.eater.eater.client.dto.order.CreateOrderRequest;
import com.eater.eater.client.dto.order.OrderDTOClient;
import com.eater.eater.client.dto.order.OrderHistoryClientDTO;

import java.util.List;

public interface OrderService {
    OrderDTOClient createOrder(CreateOrderRequest request);
    OrderDTOClient confirmOrder(Long id);
    List<OrderHistoryClientDTO> getClientOrderHistory();
}
