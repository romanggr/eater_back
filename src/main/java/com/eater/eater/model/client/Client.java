package com.eater.eater.model.client;

import com.eater.eater.enums.ClientStatus;
import com.eater.eater.enums.Role;
import com.eater.eater.model.orders.Orders;
import com.eater.eater.model.courier.CourierRating;
import com.eater.eater.model.user.User;
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
    @JsonIgnoreProperties("client")
    private List<Orders> orders;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("client")
    private List<CourierRating> rating;


    public Client(Long id, String email, String phone, String name, String password, String address, Double latitude, Double longitude, String avatarUrl, List<Orders> orders, List<CourierRating> rating) {
        super(id, email, phone, name, password, Role.CLIENT);
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.avatarUrl = avatarUrl;
        this.orders = orders;
        this.rating = rating;
    }

    public Client() {
        super.setRole(Role.CLIENT);
    }
}
