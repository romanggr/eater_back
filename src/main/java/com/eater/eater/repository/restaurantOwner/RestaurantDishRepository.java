package com.eater.eater.repository.restaurantOwner;

import com.eater.eater.model.courier.Courier;
import com.eater.eater.model.restaurantOwner.RestaurantDish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestaurantDishRepository extends JpaRepository<RestaurantDish,Long> {

}
