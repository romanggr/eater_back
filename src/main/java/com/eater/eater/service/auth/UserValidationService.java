package com.eater.eater.service.auth;

import com.eater.eater.exception.EmailAlreadyInUseException;
import com.eater.eater.exception.PhoneAlreadyInUseException;
import com.eater.eater.repository.admin.AdminRepository;
import com.eater.eater.repository.client.ClientRepository;
import com.eater.eater.repository.courier.CourierRepository;
import com.eater.eater.repository.restaurantOwner.RestaurantOwnerRepository;
import org.springframework.stereotype.Service;

@Service
public class UserValidationService {

    private final CourierRepository courierRepository;
    private final ClientRepository clientRepository;
    private final AdminRepository adminRepository;
    private final RestaurantOwnerRepository restaurantOwnerRepository;

    public UserValidationService(CourierRepository courierRepository,
                                 ClientRepository clientRepository,
                                 AdminRepository adminRepository,
                                 RestaurantOwnerRepository restaurantOwnerRepository) {
        this.courierRepository = courierRepository;
        this.clientRepository = clientRepository;
        this.adminRepository = adminRepository;
        this.restaurantOwnerRepository = restaurantOwnerRepository;
    }

    public void validateUniquePhone(String newPhone, String currentPhone) {
        if (newPhone == null || newPhone.isEmpty()) {
            throw new IllegalArgumentException("Phone number cannot be empty.");
        }

        if (!newPhone.equals(currentPhone)) {
            if (newPhone.length() < 8 || newPhone.length() > 12) {
                throw new IllegalArgumentException("Please provide a valid phone number.");
            }

            if (courierRepository.existsByPhone(newPhone) ||
                    clientRepository.existsByPhone(newPhone) ||
                    adminRepository.existsByPhone(newPhone) ||
                    restaurantOwnerRepository.existsByPhone(newPhone)) {
                throw new PhoneAlreadyInUseException("Phone already in use, choose another one");
            }
        }
    }

    public void validateUniqueEmail(String newEmail, String currentEmail) {
        if (newEmail == null || newEmail.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty.");
        }

        if (!newEmail.equals(currentEmail)) {
            if (!newEmail.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")) {
                throw new IllegalArgumentException("Please provide a valid email.");
            }

            if (courierRepository.existsByEmail(newEmail) ||
                    clientRepository.existsByEmail(newEmail) ||
                    adminRepository.existsByEmail(newEmail) ||
                    restaurantOwnerRepository.existsByEmail(newEmail)) {
                throw new EmailAlreadyInUseException("Email already in use, choose another one");
            }
        }
    }

    public void validatePassword(String password) {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty.");
        }
        if (password.length() < 8) {
            throw new IllegalArgumentException("Password must be at least 8 characters long.");
        }
        if (!password.matches(".*\\d.*")) {
            throw new IllegalArgumentException("Password must contain at least one digit.");
        }
        if (!password.matches(".*[a-zA-Z].*")) {
            throw new IllegalArgumentException("Password must contain at least one letter.");
        }
    }

    public void validateUser(String phone, String oldPhone, String email, String oldEmail, String password) {
        validateUniquePhone(phone, oldPhone);
        validateUniqueEmail(email, oldEmail);
        validatePassword(password);
    }

    public void validateUser(String phone, String oldPhone, String email, String oldEmail) {
        validateUniquePhone(phone, oldPhone);
        validateUniqueEmail(email, oldEmail);
    }
}
