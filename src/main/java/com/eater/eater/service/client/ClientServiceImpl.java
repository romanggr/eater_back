package com.eater.eater.service.client;

import com.eater.eater.dto.client.ClientDTO;
import com.eater.eater.dto.courier.CourierCoordinatesDTO;
import com.eater.eater.dto.courier.CourierRatingDTO;
import com.eater.eater.dto.orders.CreateOrderRequest;
import com.eater.eater.dto.orders.MenuRequest;
import com.eater.eater.dto.orders.OrderDTOClient;
import com.eater.eater.dto.orders.OrderHistoryClientDTO;
import com.eater.eater.enums.OrderStatus;
import com.eater.eater.model.client.Client;
import com.eater.eater.model.courier.Courier;
import com.eater.eater.model.courier.CourierCoordinates;
import com.eater.eater.model.courier.CourierRating;
import com.eater.eater.model.orders.OrderMenu;
import com.eater.eater.model.orders.Orders;
import com.eater.eater.model.restaurantOwner.RestaurantDish;
import com.eater.eater.model.restaurantOwner.RestaurantOwner;
import com.eater.eater.repository.client.ClientRepository;
import com.eater.eater.repository.courier.CourierRatingRepository;
import com.eater.eater.repository.courier.CourierRepository;
import com.eater.eater.repository.orders.OrdersRepository;
import com.eater.eater.repository.restaurantOwner.RestaurantDishRepository;
import com.eater.eater.repository.restaurantOwner.RestaurantOwnerRepository;
import com.eater.eater.security.SecurityUtil;
import com.eater.eater.service.googleCloud.DistanceService;
import com.eater.eater.service.orders.OrdersServiceImpl;
import com.eater.eater.utils.mapper.client.ClientMapper;
import com.eater.eater.utils.mapper.courier.CourierMapper;
import com.eater.eater.utils.mapper.courier.CourierRatingMapper;
import com.eater.eater.utils.mapper.orders.OrdersMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final CourierRepository courierRepository;
    private final CourierRatingRepository courierRatingRepository;
    private final OrdersServiceImpl ordersService;
    private final RestaurantOwnerRepository restaurantOwnerRepository;
    private final RestaurantDishRepository restaurantDishRepository;
    private final DistanceService distanceService;
    private final OrdersRepository ordersRepository;

    public ClientDTO getClient() {
        Long currentUserId = SecurityUtil.getCurrentUserId(Client.class);
        Client currentClient = clientRepository.findById(currentUserId).orElseThrow(
                () -> new EntityNotFoundException("Client not found"));

        SecurityUtil.validateUserIsBanned(currentClient.getClientStatus());

        return ClientMapper.toDTO(currentClient);
    }


    public CourierCoordinatesDTO getCourierCoordinates(Long courierId) {
        Courier courier = courierRepository.findById(courierId).orElseThrow(
                () -> new EntityNotFoundException("Courier with id: " + courierId + " not found"));
        CourierCoordinates coordinates = courier.getCoordinates();

        return CourierMapper.coordinatesToDTO(coordinates);
    }


    public CourierRatingDTO setCourierRating(CourierRatingDTO courierRatingDTO) {
        if (courierRatingDTO.getRating() > 5 || courierRatingDTO.getRating() < 1) {
            throw new IllegalArgumentException("The rating should be from 1 to 5");
        }

        Courier courier = courierRepository.findById(courierRatingDTO.getCourierId())
                .orElseThrow(() -> new EntityNotFoundException("Courier with id: " + courierRatingDTO.getCourierId() + " not found"));

        Client client = clientRepository.findById(courierRatingDTO.getClientId())
                .orElseThrow(() -> new EntityNotFoundException("Client with id: " + courierRatingDTO.getClientId() + " not found"));

        CourierRating courierRating = CourierRatingMapper.toEntity(courierRatingDTO, courier, client);
        courierRatingRepository.save(courierRating);

        return CourierRatingMapper.toDTO(courierRating);
    }


    @Override
    @Transactional
    public OrderDTOClient createOrder(CreateOrderRequest request) {
        Long currentUserId = SecurityUtil.getCurrentUserId(Client.class);
        Client client = clientRepository.findById(currentUserId)
                .orElseThrow(() -> new EntityNotFoundException("Client not found"));

        RestaurantOwner restaurantOwner = restaurantOwnerRepository.findRestaurantOwnerByRestaurantId(request.getRestaurantId())
                .orElseThrow(() -> new EntityNotFoundException("Restaurant owner not found"));

        Courier courier = ordersService.findCourier(
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

        return OrdersMapper.toEntity(client, courier, restaurantOwner, distance);
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
