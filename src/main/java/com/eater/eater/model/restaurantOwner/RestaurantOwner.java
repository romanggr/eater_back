package com.eater.eater.model.restaurantOwner;

import com.eater.eater.enums.RestaurantOwnerStatus;
import com.eater.eater.enums.Role;
import com.eater.eater.model.orders.Orders;
import com.eater.eater.model.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
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


    public RestaurantOwner(){
        super.setRole(Role.RESTAURANT_OWNER);
    }
}
