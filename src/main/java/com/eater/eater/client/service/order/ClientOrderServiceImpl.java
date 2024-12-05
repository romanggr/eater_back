package com.eater.eater.client.service.order;

import com.eater.eater.client.dto.order.CreateOrderRequest;
import com.eater.eater.client.dto.order.MenuRequest;
import com.eater.eater.client.dto.order.OrderDTOClient;
import com.eater.eater.client.dto.order.OrderHistoryClientDTO;
import com.eater.eater.client.model.Client;
import com.eater.eater.client.repository.ClientRepository;
import com.eater.eater.client.utils.ClientMapper;
import com.eater.eater.client.utils.OrderMapper;
import com.eater.eater.courier.model.Courier;
import com.eater.eater.enums.OrderStatus;
import com.eater.eater.model.order.OrderMenu;
import com.eater.eater.model.order.Orders;
import com.eater.eater.repository.order.OrdersRepository;
import com.eater.eater.restaurantOwner.model.RestaurantDish;
import com.eater.eater.restaurantOwner.model.RestaurantOwner;
import com.eater.eater.restaurantOwner.repository.RestaurantDishRepository;
import com.eater.eater.restaurantOwner.repository.RestaurantOwnerRepository;
import com.eater.eater.security.SecurityUtil;
import com.eater.eater.service.FindCouriee.FindCourierService;
import com.eater.eater.service.googleCloud.DistanceService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientOrderServiceImpl implements OrderService {
    private final ClientRepository clientRepository;
    private final FindCourierService findCourierService;
    private final RestaurantOwnerRepository restaurantOwnerRepository;
    private final RestaurantDishRepository restaurantDishRepository;
    private final DistanceService distanceService;
    private final OrdersRepository ordersRepository;

    @Override
    @Transactional
    public OrderDTOClient createOrder(CreateOrderRequest request) {
        Long currentUserId = SecurityUtil.getCurrentUserId(Client.class);
        Client client = clientRepository.findById(currentUserId)
                .orElseThrow(() -> new EntityNotFoundException("Client not found"));

        RestaurantOwner restaurantOwner = restaurantOwnerRepository.findRestaurantOwnerByRestaurantId(request.getRestaurantId())
                .orElseThrow(() -> new EntityNotFoundException("Restaurant owner not found"));

        Courier courier = findCourierService.findCourier(
                restaurantOwner.getRestaurant().getLatitude(),
                restaurantOwner.getRestaurant().getLongitude()
        );

        Orders order = setData(client, courier, restaurantOwner);
        setMenu(order, request.getMenu());
        Orders dbOrder = ordersRepository.save(order);

        return ClientMapper.orderToDTO(dbOrder);
    }

    private Orders setData(Client client, Courier courier, RestaurantOwner restaurantOwner) {
        int distance = distanceService.getDistance(
                courier.getCoordinates().getLatitude(),
                courier.getCoordinates().getLongitude(),
                client.getLatitude(),
                client.getLongitude(),
                restaurantOwner.getRestaurant().getLatitude(),
                restaurantOwner.getRestaurant().getLongitude()
        );

        return OrderMapper.toEntity(client, courier, restaurantOwner, distance);
    }

    private void setMenu(Orders order, List<MenuRequest> requestMenu) {
        if (requestMenu == null || requestMenu.isEmpty()) {
            throw new IllegalArgumentException("Menu items cannot be null or empty");
        }

        List<OrderMenu> orderMenus = requestMenu.stream()
                .map(menuRequest -> {
                    RestaurantDish dish = restaurantDishRepository.findById(menuRequest.getDishId())
                            .orElseThrow(() -> new EntityNotFoundException("Dish not found"));
                    if (menuRequest.getQuantity() <= 0) {
                        throw new IllegalArgumentException("Dish quantity must be greater than zero");
                    }
                    OrderMenu orderMenu = new OrderMenu();
                    orderMenu.setDish(dish);
                    orderMenu.setDishQuantity(menuRequest.getQuantity());
                    orderMenu.setOrder(order);
                    return orderMenu;
                })
                .toList();

        double menuPrice = orderMenus.stream()
                .mapToDouble(orderMenu -> orderMenu.getDish().getPrice() * orderMenu.getDishQuantity())
                .sum();

        order.setOrderMenus(orderMenus);
        order.setRestaurantEarnings(menuPrice);
        order.setTotalPrice(menuPrice + order.getCourierEarnings() + order.getAppEarnings());
    }


    @Override
    public OrderDTOClient confirmOrder(Long id) {
        Orders order = ordersRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Order not found"));

        order.setFinishedAt(LocalDateTime.now());
        order.setStatus(OrderStatus.FINISHED);
        Orders updatedOrder = ordersRepository.save(order);

        return ClientMapper.orderToDTO(updatedOrder);
    }

    @Override
    public List<OrderHistoryClientDTO> getClientOrderHistory() {
        Long currentUserId = SecurityUtil.getCurrentUserId(Client.class);

        List<Orders> orders = ordersRepository.findOrdersByClientId(currentUserId)
                .orElseThrow(() -> new EntityNotFoundException("Orders not found"));

        return orders.stream()
                .map(ClientMapper::toOrderHistoryDTO)
                .toList();
    }
}
