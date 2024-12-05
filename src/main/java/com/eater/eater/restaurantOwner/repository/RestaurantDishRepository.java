package com.eater.eater.restaurantOwner.repository;

import com.eater.eater.restaurantOwner.model.RestaurantDish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface RestaurantDishRepository extends JpaRepository<RestaurantDish, Long> {
    List<RestaurantDish> findByRestaurantId(Long restaurantId);

    @Modifying
    @Transactional
    @Query("DELETE FROM RestaurantDish d WHERE d.id = :id")
    void deleteById(@Param("id") Long id);
}
