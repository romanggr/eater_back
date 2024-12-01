package com.eater.eater.security;

import com.eater.eater.exception.UnverifiedEmailException;
import com.eater.eater.model.email.EmailConfirmationModel;
import com.eater.eater.repository.emailConfirmation.EmailConfirmationRepository;
import com.eater.eater.service.auth.UserValidationService;
import com.eater.eater.service.email.EmailScripts;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;

@Service
@RequiredArgsConstructor
public class EmailConfirmation {
    private final EmailScripts emailScripts;
    private final EmailConfirmationRepository emailConfirmationRepository;
    private final UserValidationService userValidationService;


    private int generateCode() {
        SecureRandom secureRandom = new SecureRandom();
        return 1000 + secureRandom.nextInt(9000);
    }

    public void sendCode(String userEmail, int code) {
        emailScripts.verifyEmail(userEmail, code);
    }

    private void saveCode(String email, int code) {
        EmailConfirmationModel emailConfirmationModel = new EmailConfirmationModel();
        emailConfirmationModel.setEmail(email);
        emailConfirmationModel.setCode(code);

        emailConfirmationRepository.save(emailConfirmationModel);
    }

    public void startVerification(String userEmail) {
//        int code = generateCode();
        int code = 1111;
        sendCode(userEmail, code);
        saveCode(userEmail, code);
    }

    public void verifyEmailCode(String email, int code) {
        userValidationService.validateUniqueEmail(email, null);

        EmailConfirmationModel model = emailConfirmationRepository.findLatestByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("No email confirmation record found for: " + email));

        if (model.getCode() != code) {
            throw new UnverifiedEmailException("Invalid email confirmation code.");
        }
        // send email to user that his account was confirmed
        emailScripts.successVerificationEmail(email);

        // delete for saving place in db
        emailConfirmationRepository.delete(model);
    }

}
