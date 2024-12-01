package com.eater.eater.service.courier;

import com.eater.eater.dto.courier.*;
import com.eater.eater.dto.orders.OrderDTOCourier;
import com.eater.eater.dto.orders.OrderHistoryCourierDTO;
import com.eater.eater.enums.CourierStatus;
import com.eater.eater.enums.OrderStatus;
import com.eater.eater.exception.StatusException;
import com.eater.eater.model.courier.Courier;
import com.eater.eater.model.courier.CourierCoordinates;
import com.eater.eater.model.orders.Orders;
import com.eater.eater.repository.courier.CourierCoordinatesRepository;
import com.eater.eater.repository.courier.CourierRepository;
import com.eater.eater.repository.orders.OrdersRepository;
import com.eater.eater.security.SecurityUtil;
import com.eater.eater.utils.mapper.courier.CourierMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CourierServiceImpl implements CourierService {
    private final CourierRepository courierRepository;
    private final CourierCoordinatesRepository courierCoordinatesRepository;
    private final OrdersRepository ordersRepository;


    public CourierDTO getCourier() {
        Long currentUserId = SecurityUtil.getCurrentUserId(Courier.class);
        Courier courier = courierRepository.findById(currentUserId).orElseThrow(
                () -> new EntityNotFoundException("Courier not found"));

        SecurityUtil.validateUserIsBanned(courier.getCourierStatus());

        return CourierMapper.toDTO(courier);
    }


    public CourierDTO updateCourierCoordinates(CourierCoordinatesDTO courierCoordinatesDTO) {
        Courier currentUser = SecurityUtil.getCurrentUser(Courier.class);
        Courier currentCourier = courierRepository.findById(currentUser.getId())
                .orElseThrow(() -> new EntityNotFoundException("Courier not found"));
        CourierCoordinates coordinates = currentCourier.getCoordinates();

        if (coordinates == null) {
            throw new EntityNotFoundException("Coordinates not found for the current courier");
        }

        courierCoordinatesRepository.save(CourierMapper.coordinatesToEntity(coordinates, courierCoordinatesDTO));
        return CourierMapper.toDTO(currentCourier);
    }


    public CourierDTO updateIsActive(CourierIsActiveDTO isActiveDTO) {
        Courier currentUser = SecurityUtil.getCurrentUser(Courier.class);

        Courier currentCourier = courierRepository.findById(currentUser.getId())
                .orElseThrow(() -> new EntityNotFoundException("Courier not found"));

        SecurityUtil.validateCourierStatus(currentCourier.getCourierStatus());

        currentCourier.setCourierStatus(isActiveDTO.getIsActive() ? CourierStatus.AVAILABLE : CourierStatus.OFF_DUTY);
        courierRepository.save(currentCourier);

        return CourierMapper.toDTO(currentCourier);
    }

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

        if(!order.getCourier().getId().equals(currentUserId)) throw new StatusException("It isn't order of this courier. Courier can set order delivered if it is his order");
        order.setStatus(OrderStatus.APPROVED_BY_COURIER);

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
