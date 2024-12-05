package com.eater.eater.email.service;

import org.springframework.stereotype.Service;

@Service
public class EmailScripts {
    private final EmailSender emailSender;

    public EmailScripts(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void deleteAdminByAdmin(String to, String fullName) {
        emailSender.sendEmail(
                to,
                "Deleted account",
                String.format("Hello %s, your account was deleted.", fullName)
        );
    }

    public void banUserByAdmin(String to, String fullName, String reason) {
        emailSender.sendEmail(
                to,
                "Banned account",
                String.format("Hello %s, your account was banned. For %s", fullName, reason)
        );
    }

    public void confirmUserByAdmin(String to, String fullName) {
        emailSender.sendEmail(
                to,
                "Confirmed account",
                String.format("Hello %s, your account was confirmed by admin. You can use all functions of the app", fullName)
        );
    }

    public void verifyEmail(String to, int code) {
        emailSender.sendEmail(
                to,
                "Verify code",
                String.format("Hello, your verify code is %d", code)
        );
    }

    public void successVerificationEmail(String to) {
        emailSender.sendEmail(
                to,
                "Verify code",
                "Hello, your email-verification code was successful."
        );
    }
}
