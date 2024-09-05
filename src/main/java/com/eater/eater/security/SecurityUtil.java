package com.eater.eater.security;

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
}
