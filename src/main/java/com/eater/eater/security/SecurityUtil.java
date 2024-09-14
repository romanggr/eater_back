package com.eater.eater.security;

import com.eater.eater.enums.ClientStatus;
import com.eater.eater.enums.CourierStatus;
import com.eater.eater.enums.RestaurantOwnerStatus;
import com.eater.eater.exception.BannedStatusException;
import com.eater.eater.exception.StatusException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {
    public static <T> T getCurrentUser(Class<T> clazz) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return clazz.cast(authentication.getPrincipal());
    }

    public static <T> Long getCurrentUserId(Class<T> clazz) {
        T currentUser = getCurrentUser(clazz);
        try {
            return (Long) currentUser.getClass().getMethod("getId").invoke(currentUser);
        } catch (Exception e) {
            throw new IllegalStateException("Failed to get user ID", e);
        }
    }

    public static void validateCourierStatus(CourierStatus courierStatus) {
        if (courierStatus == CourierStatus.UNCONFIRMED) {
            throw new StatusException("Your account is not confirmed yet. Please contact support.");
        }
        if (courierStatus == CourierStatus.ON_DELIVERY) {
            throw new StatusException("You have an order right now. First deliver it and then change the status");
        }
        if (courierStatus == CourierStatus.BANNED) {
            throw new BannedStatusException("Your account was banned please check your email");
        }
    }

    public static void validateUserIsBanned(CourierStatus status) {
        if (status == CourierStatus.BANNED) {
            throw new BannedStatusException("Your account was banned, please check your email");
        }
    }

    public static void validateUserIsBanned(ClientStatus status) {
        if (status == ClientStatus.BANNED) {
            throw new BannedStatusException("Your account was banned, please check your email");
        }
    }

    public static void validateUserIsBanned(RestaurantOwnerStatus status) {
        if (status == RestaurantOwnerStatus.BANNED) {
            throw new BannedStatusException("Your account was banned, please check your email");
        }
    }
}
