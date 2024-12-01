package com.eater.eater.repository.emailConfirmation;

import com.eater.eater.model.email.EmailConfirmationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmailConfirmationRepository extends JpaRepository<EmailConfirmationModel, Long> {
    @Query("SELECT e FROM EmailConfirmationModel e WHERE e.email = :email ORDER BY e.id DESC LIMIT 1")
    Optional<EmailConfirmationModel> findLatestByEmail(@Param("email") String email);
}
