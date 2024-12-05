package com.eater.eater.courier.repository;

import com.eater.eater.courier.model.CourierCoordinates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourierCoordinatesRepository extends JpaRepository<CourierCoordinates,Long> {
}
