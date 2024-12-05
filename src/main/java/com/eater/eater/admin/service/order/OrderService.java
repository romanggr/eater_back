package com.eater.eater.admin.service.order;

import com.eater.eater.admin.dto.order.OrderDTO;

import java.util.List;

public interface OrderService {
    List<OrderDTO> getOrders();

    OrderDTO getOrderById(Long id);
}
