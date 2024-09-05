package com.eater.eater.repository.courier;

import com.eater.eater.model.courier.CourierRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourierRatingRepository extends JpaRepository<CourierRating, Long> {

}