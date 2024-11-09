package com.eater.eater.repository.restaurantOwner;

import com.eater.eater.model.restaurantOwner.RestaurantDish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface RestaurantDishRepository extends JpaRepository<RestaurantDish, Long> {
    List<RestaurantDish> findByRestaurantId(Long restaurantId); // New method

    @Modifying
    @Transactional
    @Query("DELETE FROM RestaurantDish d WHERE d.id = :id")
    void deleteById(Long id);

}
