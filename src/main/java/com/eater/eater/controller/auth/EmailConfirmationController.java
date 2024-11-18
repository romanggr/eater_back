package com.eater.eater.controller.auth;

import com.eater.eater.security.EmailConfirmation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class EmailConfirmationController {
    private final EmailConfirmation emailConfirmation;

    public EmailConfirmationController(EmailConfirmation emailConfirmation) {
        this.emailConfirmation = emailConfirmation;
    }

    @PostMapping("/confirmEmail/{email}")
    public ResponseEntity<String> confirmEmail(@PathVariable String email){
        emailConfirmation.startVerification(email);
        return ResponseEntity.ok("Verification email sent successfully.");
    }
}
