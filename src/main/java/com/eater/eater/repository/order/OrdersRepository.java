package com.eater.eater.repository.order;

import com.eater.eater.enums.OrderStatus;
import com.eater.eater.model.order.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {
    @Query("SELECT o FROM Orders o WHERE o.status = :status AND o.courier.id = :id")
    Optional<Orders> findNewOrderByCourierId(@Param("id") Long id, @Param("status") OrderStatus status);


    @Query("SELECT o FROM Orders o WHERE o.courier.id = :id")
    List<Orders> findOrdersByCourierId(@Param("id") Long id);

    @Query("SELECT o FROM Orders o WHERE o.client.id = :id")
    Optional<List<Orders>> findOrdersByClientId(@Param("id") Long id);

    @Query("SELECT o FROM Orders o WHERE o.status = 'CREATED' AND o.restaurantOwner.id = :id")
    Optional<List<Orders>> findNewOrdersOfRestaurant(@Param("id") Long id);

    @Query("SELECT o FROM Orders o WHERE o.restaurantOwner.id = :id")
    Optional<List<Orders>> findAllOrdersOfRestaurant(@Param("id") Long id);

    @Query("SELECT o FROM Orders o WHERE o.restaurantOwner.id = :id AND o.id = :orderId")
    Optional<Orders> findOrderOfRestaurant(@Param("id") Long id, @Param("orderId") Long orderId);
}

