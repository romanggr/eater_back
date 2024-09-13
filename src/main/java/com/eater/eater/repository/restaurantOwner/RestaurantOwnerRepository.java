package com.eater.eater.repository.restaurantOwner;

import com.eater.eater.model.client.Client;
import com.eater.eater.model.restaurantOwner.RestaurantOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantOwnerRepository extends JpaRepository<RestaurantOwner,Long> {
    Optional<RestaurantOwner> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
    List<RestaurantOwner> findAllByPhone(String phone);
}
