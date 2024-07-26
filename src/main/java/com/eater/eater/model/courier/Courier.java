package com.eater.eater.model.courier;

import com.eater.eater.enums.Role;
import com.eater.eater.enums.TransportType;
import com.eater.eater.model.Orders;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "courier")
public class Courier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String password;
    private String avatarUrl;
    private boolean isActive;

    @Enumerated(EnumType.STRING)
    private TransportType transportType;

    @Enumerated(EnumType.STRING)
    private Role role;


    @OneToMany(mappedBy = "courier")
    @JsonIgnoreProperties("courier")
    private List<Orders> orders;

    @OneToOne(mappedBy = "courier", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("courier")
    private CourierCoordinates coordinates;

    @OneToOne(mappedBy = "courier", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("courier")
    private CourierRating rating;
}
