package com.eater.eater.repository.restaurantOwner;

import com.eater.eater.model.restaurantOwner.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    Optional<Restaurant> findRestaurantByRestaurantOwnerId(Long id);

}
