package com.eater.eater.config;

import com.eater.eater.repository.admin.AdminRepository;
import com.eater.eater.repository.client.ClientRepository;
import com.eater.eater.repository.courier.CourierRepository;
import com.eater.eater.repository.restaurantOwner.RestaurantOwnerRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class ApplicationConfiguration {
    private final CourierRepository courierRepository;
    private final ClientRepository clientRepository;
    private final AdminRepository adminRepository;
    private final RestaurantOwnerRepository restaurantOwnerRepository;


    public ApplicationConfiguration(CourierRepository courierRepository, ClientRepository clientRepository, AdminRepository adminRepository, RestaurantOwnerRepository restaurantOwnerRepository) {
        this.courierRepository = courierRepository;
        this.clientRepository = clientRepository;
        this.adminRepository = adminRepository;
        this.restaurantOwnerRepository = restaurantOwnerRepository;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            UserDetails user = courierRepository.findByEmail(username).orElse(null);
            if (user == null) {
                user = clientRepository.findByEmail(username).orElse(null);
            }
            if (user == null) {
                user = adminRepository.findByEmail(username).orElse(null);
            }
            if (user == null) {
                user = restaurantOwnerRepository.findByEmail(username).orElse(null);
            }
            if (user == null) {
                throw new UsernameNotFoundException("User not found");
            }

            return user;
        };
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }
}