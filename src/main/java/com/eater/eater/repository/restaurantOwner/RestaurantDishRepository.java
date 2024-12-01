package com.eater.eater.repository.restaurantOwner;

import com.eater.eater.model.restaurantOwner.RestaurantDish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantDishRepository extends JpaRepository<RestaurantDish, Long> {
    List<RestaurantDish> findByRestaurantId(Long restaurantId);

    @Query("SELECT rd.price FROM RestaurantDish rd WHERE rd.id = :id")
    Optional<Double> findPriceById(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query("DELETE FROM RestaurantDish d WHERE d.id = :id")
    void deleteById(Long id);
}
