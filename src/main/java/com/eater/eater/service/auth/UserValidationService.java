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

    public void validateUniqueEmail(String email) {
        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")) {
            throw new IllegalArgumentException("Please provide a real email");

        }

        if (courierRepository.existsByEmail(email) ||
                clientRepository.existsByEmail(email) ||
                adminRepository.existsByEmail(email) ||
                restaurantOwnerRepository.existsByEmail(email)) {
            throw new EmailAlreadyInUseException("Email already in use, choose another one");
        }
    }

    public void validateUniquePhone(String phone) {
        if (phone.length() < 8 || phone.length() > 12) {
            throw new IllegalArgumentException("Please provide a real phone number");
        }

        if (courierRepository.existsByPhone(phone) ||
                clientRepository.existsByPhone(phone) ||
                adminRepository.existsByPhone(phone) ||
                restaurantOwnerRepository.existsByPhone(phone)) {
            throw new PhoneAlreadyInUseException("Phone already in use, choose another one");
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

    public void validateUser(String phone, String email, String password) {
        validateUniquePhone(phone);
        validateUniqueEmail(email);
        validatePassword(password);
    }

    public void validateUser(String phone, String email) {
        validateUniquePhone(phone);
        validateUniqueEmail(email);
    }
}
