package com.eater.eater.model.client;

import com.eater.eater.enums.ClientStatus;
import com.eater.eater.enums.Role;
import com.eater.eater.exception.AdminUnverifiedException;
import com.eater.eater.model.orders.Orders;
import com.eater.eater.model.courier.CourierRating;
import com.eater.eater.model.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@DiscriminatorValue("CLIENT")
public class Client extends User implements UserDetails {
    private String address;
    private Double latitude;
    private Double longitude;
    private String avatarUrl;

    @Enumerated(EnumType.STRING)
    private ClientStatus clientStatus = ClientStatus.ACTIVE;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Orders> orders;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<CourierRating> rating;


    public Client() {
        super.setRole(Role.CLIENT);
    }

    @Override
    public boolean isEnabled() {
        super.isEnabled();
        if (clientStatus.equals(ClientStatus.BANNED)) {
            throw new AdminUnverifiedException("Your account is banned");
        }
        return true;
    }
}
