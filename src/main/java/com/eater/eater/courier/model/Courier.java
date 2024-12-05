package com.eater.eater.courier.model;

import com.eater.eater.enums.CourierStatus;
import com.eater.eater.enums.Role;
import com.eater.eater.enums.TransportType;
import com.eater.eater.exception.StatusException;
import com.eater.eater.model.order.Orders;
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
@Builder
@AllArgsConstructor
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


    public Courier() {
        super.setRole(Role.COURIER);
    }

    @Override
    public boolean isEnabled() {
        super.isEnabled();
        if (courierStatus.equals(CourierStatus.BANNED)) {
            throw new StatusException("Your account is banned.");
        }
        if (courierStatus.equals(CourierStatus.UNCONFIRMED)) {
            throw new StatusException("Your account is unconfirmed. Wait before admin accept your application");
        }
        return true;
    }
}
