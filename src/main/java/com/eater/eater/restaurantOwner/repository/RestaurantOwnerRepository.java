package com.eater.eater.restaurantOwner.repository;

import com.eater.eater.restaurantOwner.model.RestaurantOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestaurantOwnerRepository extends JpaRepository<RestaurantOwner,Long> {
    Optional<RestaurantOwner> findByEmail(String email);
    Optional<RestaurantOwner> findRestaurantOwnerByRestaurantId(Long restaurantId);
}
