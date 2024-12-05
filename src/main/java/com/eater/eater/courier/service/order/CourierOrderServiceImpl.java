package com.eater.eater.courier.service.order;

import com.eater.eater.courier.dto.order.OrderDTOCourier;
import com.eater.eater.courier.dto.order.OrderHistoryCourierDTO;
import com.eater.eater.courier.model.Courier;
import com.eater.eater.courier.utils.CourierMapper;
import com.eater.eater.enums.OrderStatus;
import com.eater.eater.exception.StatusException;
import com.eater.eater.model.order.Orders;
import com.eater.eater.repository.order.OrdersRepository;
import com.eater.eater.security.SecurityUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourierOrderServiceImpl implements OrderService {
    private final OrdersRepository ordersRepository;

    @Override
    public OrderDTOCourier getOrder() {
        Long currentUserId = SecurityUtil.getCurrentUserId(Courier.class);
        Orders order = ordersRepository.findNewOrderByCourierId(currentUserId, OrderStatus.CREATED)
                .orElseThrow(() -> new EntityNotFoundException("Aren't available orders"));

        return CourierMapper.orderToDTO(order);
    }

    @Override
    public Long setOrderDelivered(Long id) {
        Long currentUserId = SecurityUtil.getCurrentUserId(Courier.class);
        Orders order = ordersRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));

        if (!order.getStatus().equals(OrderStatus.CREATED)) {
            throw new IllegalArgumentException("Order is already delivered");
        }

        if (!order.getCourier().getId().equals(currentUserId))
            throw new StatusException("It isn't order of this courier. Courier can set order delivered if it is his order");
        order.setStatus(OrderStatus.APPROVED_BY_COURIER);
        order.setFinishedAt(LocalDateTime.now());

        Orders updatedOrder = ordersRepository.save(order);
        return updatedOrder.getId();
    }

    @Override
    public List<OrderHistoryCourierDTO> getOrdersHistory() {
        Long currentUserId = SecurityUtil.getCurrentUserId(Courier.class);

        List<Orders> orders = ordersRepository.findOrdersByCourierId(currentUserId);

        return orders.stream()
                .map(CourierMapper::toOrderHistoryDTO)
                .toList();
    }

}
