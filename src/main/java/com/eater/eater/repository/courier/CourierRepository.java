package com.eater.eater.repository.courier;

import com.eater.eater.enums.CourierStatus;
import com.eater.eater.model.courier.Courier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourierRepository extends JpaRepository<Courier, Long> {
    Optional<Courier> findByEmail(String email);

    @Query("SELECT c FROM Courier c WHERE c.courierStatus = :status")
    List<Courier> findAvailableCouriers(@Param("status" ) CourierStatus status);
}
