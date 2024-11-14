package com.eater.eater.service.auth;

import com.eater.eater.enums.Role;
import com.eater.eater.exception.EmailAlreadyInUseException;
import com.eater.eater.repository.admin.AdminRepository;
import com.eater.eater.repository.client.ClientRepository;
import com.eater.eater.repository.courier.CourierRepository;
import com.eater.eater.repository.restaurantOwner.RestaurantOwnerRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

    public void validatePhone(String phone) {
        if (phone == null || phone.isEmpty()) {
            throw new IllegalArgumentException("Phone number cannot be empty.");
        } else if (phone.length() < 8 || phone.length() > 14) {
            throw new IllegalArgumentException("Please provide a valid phone number.");
        }
    }

    public void validateEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty.");

        } else if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")) {
            throw new IllegalArgumentException("Please provide a valid email.");
        }
    }

    public void validateUniqueEmail(String email, String oldEmail, Role role) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty.");
        }

        validateEmail(email);

        boolean exists = switch (role) {
            case COURIER -> courierRepository.existsByEmail(email);
            case CLIENT -> clientRepository.existsByEmail(email);
            case ADMIN -> adminRepository.existsByEmail(email);
            case RESTAURANT_OWNER -> restaurantOwnerRepository.existsByEmail(email);
            default -> throw new IllegalArgumentException("Unsupported role.");
        };

        if (exists && !email.equals(oldEmail)) {
            throw new EmailAlreadyInUseException("Email already in use, choose another one");
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

    public void isEqualPassword(String currentPassword, String encryptedCurrentPassword, PasswordEncoder passwordEncoder) {
        if (!passwordEncoder.matches(currentPassword, encryptedCurrentPassword)) {
            throw new IllegalArgumentException("Passwords do not match");
        }
    }

    public void signUpValidation(String phone, String email, String password, Role role) {
        validatePhone(phone);
        validateUniqueEmail(email, null, role);
        validatePassword(password);
    }

    public void signUpValidation(String phone, String email, String password, Role role, MultipartFile file) {
        validatePhone(phone);
        validateUniqueEmail(email, null, role);
        validatePassword(password);
        avatarValidation(file);
    }

    public void updateValidation(String phone, String email, String oldEmail, Role role, String currentPassword, String encryptedCurrentPassword, PasswordEncoder passwordEncoder) {
        validatePhone(phone);
        validateUniqueEmail(email, oldEmail, role);
        isEqualPassword(currentPassword, encryptedCurrentPassword, passwordEncoder);
    }

    public void updatePasswordValidation(String newPassword, String currentPassword, String encryptedCurrentPassword, PasswordEncoder passwordEncoder) {
        validatePassword(newPassword);
        isEqualPassword(currentPassword, encryptedCurrentPassword, passwordEncoder);
    }

    public void avatarValidation(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("Avatar cannot be empty.");
        }

        String fileName = file.getOriginalFilename().toLowerCase();
        if (!fileName.endsWith(".png") && !fileName.endsWith(".jpg") && !fileName.endsWith(".jpeg") && !fileName.endsWith(".webp")) {
            throw new IllegalArgumentException("Unsupported image file extension. You can provide .png, .jpg, .jpeg, .webp");
        }

        if (file.getSize() > 400000) {
            throw new IllegalArgumentException("Avatar size exceeds 4 kilobytes.");
        }
    }

}
