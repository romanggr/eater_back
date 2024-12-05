package com.eater.eater.admin.service.order;

import com.eater.eater.admin.dto.order.OrderDTO;
import com.eater.eater.admin.utils.AdminMapper;
import com.eater.eater.model.order.Orders;
import com.eater.eater.repository.order.OrdersRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminOrderServiceImpl implements OrderService{
    private final OrdersRepository ordersRepository;

    @Override
    public List<OrderDTO> getOrders() {
        List<Orders> orders = ordersRepository.findAll();

        return orders.stream().map(AdminMapper::toOrderDTO).toList();
    }

    @Override
    public OrderDTO getOrderById(Long id) {
        Orders order = ordersRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Order not found"));

        return AdminMapper.toOrderDTO(order);
    }
}
