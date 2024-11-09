package com.eater.eater.service.auth;

import com.eater.eater.dto.auth.AuthResponse;
import com.eater.eater.model.user.User;
import com.eater.eater.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthUtilityService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthUtilityService(AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }


    public Authentication addContext(String email, String password) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return authentication;
    }

    public <T extends User, S> AuthResponse<S> createAuthResponse(T user, S userDTO) {
        String jwtToken = jwtService.generateToken(user);
        AuthResponse<S> authResponse = new AuthResponse<>();
        authResponse.setToken(jwtToken);
        authResponse.setUserData(userDTO);

        return authResponse;
    }
}
