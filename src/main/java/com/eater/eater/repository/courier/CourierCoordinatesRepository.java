package com.eater.eater.repository.courier;

import com.eater.eater.model.courier.CourierCoordinates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourierCoordinatesRepository extends JpaRepository<CourierCoordinates,Long> {
}
