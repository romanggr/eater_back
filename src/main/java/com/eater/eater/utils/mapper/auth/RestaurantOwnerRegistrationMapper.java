package com.eater.eater.utils.mapper.auth;

import com.eater.eater.dto.auth.AdminRegistrationRequest;
import com.eater.eater.dto.auth.RestaurantOwnerRegistrationRequest;
import com.eater.eater.dto.restaurantOwner.UpdateRestaurantOwnerRequest;
import com.eater.eater.model.admin.Admin;
import com.eater.eater.model.restaurantOwner.RestaurantOwner;
import org.springframework.security.crypto.password.PasswordEncoder;

public class RestaurantOwnerRegistrationMapper {
    public static RestaurantOwner toEntity(RestaurantOwnerRegistrationRequest input, PasswordEncoder passwordEncoder) {
        RestaurantOwner user = new RestaurantOwner();
        user.setName(input.getName());
        user.setEmail(input.getEmail());
        user.setPhone(input.getPhone());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        user.setRestaurant(input.getRestaurant());
        user.setOrders(input.getOrders());

        return user;
    }

    public static RestaurantOwner updateRequestToEntity(UpdateRestaurantOwnerRequest request, RestaurantOwner restaurantOwner) {
        if (request == null || restaurantOwner == null) return null;

        restaurantOwner.setName(request.getName());
        restaurantOwner.setPhone(request.getPhone());
        restaurantOwner.setEmail(request.getEmail());

        return restaurantOwner;
    }
}
