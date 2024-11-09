package com.eater.eater.model.courier;

import com.eater.eater.enums.CourierStatus;
import com.eater.eater.enums.Role;
import com.eater.eater.enums.TransportType;
import com.eater.eater.model.orders.Orders;
import com.eater.eater.model.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@DiscriminatorValue("COURIER")
public class Courier extends User implements UserDetails {
    private String avatarUrl;

    @Enumerated(EnumType.STRING)
    private TransportType transportType;

    @Enumerated(EnumType.STRING)
    private CourierStatus courierStatus = CourierStatus.UNCONFIRMED;

    @OneToMany(mappedBy = "courier")
    @JsonIgnoreProperties("courier")
    private List<Orders> orders;

    @OneToOne(mappedBy = "courier", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("courier")
    private CourierCoordinates coordinates;

    @OneToMany(mappedBy = "courier", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("courier")
    private List<CourierRating> rating;


    public Courier(Long id, String email, String phone, String name, String password, String avatarUrl, TransportType transportType, List<Orders> orders, CourierCoordinates coordinates, List<CourierRating> rating) {
        super(id, email, phone, name, password, Role.COURIER);
        this.avatarUrl = avatarUrl;
        this.transportType = transportType;
        this.orders = orders;
        this.coordinates = coordinates;
        this.rating = rating;
    }

    public Courier() {
        super.setRole(Role.COURIER);
    }
}
