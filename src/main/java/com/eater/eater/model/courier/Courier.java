package com.eater.eater.model.courier;

import com.eater.eater.enums.CourierStatus;
import com.eater.eater.enums.Role;
import com.eater.eater.enums.TransportType;
import com.eater.eater.model.orders.Orders;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "courier")
public class Courier implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String phone;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String avatarUrl;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TransportType transportType;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CourierStatus courierStatus = CourierStatus.UNCONFIRMED;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role = Role.COURIER;

    @OneToMany(mappedBy = "courier")
    @JsonIgnoreProperties("courier")
    private List<Orders> orders;

    @OneToOne(mappedBy = "courier", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("courier")
    private CourierCoordinates coordinates;

    @OneToMany(mappedBy = "courier", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("courier")
    private List<CourierRating> rating;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // I did it without role entity, I try to do it maximum easy
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
