package com.eater.eater.service.auth;

import com.eater.eater.enums.Role;
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

    public void validateUniquePhone(String phone, String oldPhone, Role role) {
        if (phone == null || phone.isEmpty()) {
            throw new IllegalArgumentException("Phone number cannot be empty.");
        }

        if (!phone.equals(oldPhone)) {
            if (phone.length() < 8 || phone.length() > 12) {
                throw new IllegalArgumentException("Please provide a valid phone number.");
            }

            boolean exists = switch (role) {
                case COURIER -> courierRepository.existsByPhone(phone);
                case CLIENT -> clientRepository.existsByPhone(phone);
                case ADMIN -> adminRepository.existsByPhone(phone);
                case RESTAURANT_OWNER -> restaurantOwnerRepository.existsByPhone(phone);
                default -> throw new IllegalArgumentException("Unsupported role.");
            };

            if (exists) {
                System.out.println(123);
                throw new PhoneAlreadyInUseException("Phone already in use, choose another one");
            }
        }
    }

    public void validateUniqueEmail(String newEmail, String currentEmail, Role role) {
        if (newEmail == null || newEmail.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty.");
        }

        if (!newEmail.equals(currentEmail)) {
            if (!newEmail.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")) {
                throw new IllegalArgumentException("Please provide a valid email.");
            }

            boolean exists = switch (role) {
                case COURIER -> courierRepository.existsByEmail(newEmail);
                case CLIENT -> clientRepository.existsByEmail(newEmail);
                case ADMIN -> adminRepository.existsByEmail(newEmail);
                case RESTAURANT_OWNER -> restaurantOwnerRepository.existsByEmail(newEmail);
                default -> throw new IllegalArgumentException("Unsupported role.");
            };

            if (exists) {
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

    public void validateUser(String phone, String oldPhone, String email, String oldEmail, String password, Role role) {
        validateUniquePhone(phone, oldPhone, role);
        validateUniqueEmail(email, oldEmail, role);
        validatePassword(password);
    }

    public void validateUser(String phone, String oldPhone, String email, String oldEmail, Role role) {
        validateUniquePhone(phone, oldPhone, role);
        validateUniqueEmail(email, oldEmail, role);
    }
}
