package com.eater.eater.security;


import com.eater.eater.DTO.auth.AuthResponse;
import com.eater.eater.model.user.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SecurityUtil {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public SecurityUtil(AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }


    public void addContext(String email, String password) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public <T extends User, S> AuthResponse<S> createAuthResponse(T user, S userDTO) {
        String jwtToken = jwtService.generateToken(user);
        AuthResponse<S> authResponse = new AuthResponse<>();
        authResponse.setToken(jwtToken);
        authResponse.setUserData(userDTO);

        return authResponse;
    }

    public static <T> T getCurrentUser(Class<T> clazz) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalStateException("No authenticated user found");
        }
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
