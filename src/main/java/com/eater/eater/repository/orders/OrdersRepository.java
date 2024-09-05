package com.eater.eater.repository.orders;

import com.eater.eater.model.orders.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends JpaRepository<Orders,Long> {
}
