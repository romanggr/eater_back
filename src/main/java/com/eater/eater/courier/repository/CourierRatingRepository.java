package com.eater.eater.courier.repository;

import com.eater.eater.courier.model.CourierRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourierRatingRepository extends JpaRepository<CourierRating, Long> {

}