package com.eater.eater.courier.service.order;

import com.eater.eater.courier.dto.order.OrderDTOCourier;
import com.eater.eater.courier.dto.order.OrderHistoryCourierDTO;

import java.util.List;

public interface OrderService {
    OrderDTOCourier getOrder();

    Long setOrderDelivered(Long id);

    List<OrderHistoryCourierDTO> getOrdersHistory();

}
