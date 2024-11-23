package com.eater.eater.repository.emailConfirmation;

import com.eater.eater.model.email.EmailConfirmationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmailConfirmationRepository extends JpaRepository<EmailConfirmationModel, Long> {
    Optional<EmailConfirmationModel> getReferenceByEmail(String email);

    boolean existsByEmail(String email);

    void deleteByEmail(String email);
}
