package com.eater.eater.restaurantOwner.model;

import com.eater.eater.enums.RestaurantOwnerStatus;
import com.eater.eater.enums.Role;
import com.eater.eater.exception.StatusException;
import com.eater.eater.model.order.Orders;
import com.eater.eater.model.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@DiscriminatorValue("RESTAURANT_OWNER")
public class RestaurantOwner extends User implements UserDetails {
    @Enumerated(EnumType.STRING)
    private RestaurantOwnerStatus restaurantOwnerStatus = RestaurantOwnerStatus.ACTIVE;

    @OneToOne(mappedBy = "restaurantOwner", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Restaurant restaurant;

    @OneToMany(mappedBy = "restaurantOwner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("restaurantOwner")
    private List<Orders> orders;


    public RestaurantOwner() {
        super.setRole(Role.RESTAURANT_OWNER);
    }

    @Override
    public boolean isEnabled() {
        super.isEnabled();
        if (restaurantOwnerStatus.equals(RestaurantOwnerStatus.BANNED)) {
            throw new StatusException("Your account is banned.");
        }
        return true;
    }
}
