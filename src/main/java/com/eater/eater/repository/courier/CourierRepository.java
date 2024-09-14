package com.eater.eater.repository.courier;

import com.eater.eater.model.courier.Courier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourierRepository extends JpaRepository<Courier, Long> {
    Optional<Courier> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);

    List<Courier> findAllByPhone(String phone);
}
