package com.eater.eater.model.restaurantOwner;

        import com.eater.eater.enums.RestaurantOwnerStatus;
        import com.eater.eater.enums.Role;
        import com.eater.eater.model.orders.Orders;
        import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
        import com.fasterxml.jackson.annotation.JsonManagedReference;
        import jakarta.persistence.*;
        import lombok.AllArgsConstructor;
        import lombok.Data;
        import lombok.NoArgsConstructor;
        import org.springframework.security.core.GrantedAuthority;
        import org.springframework.security.core.authority.SimpleGrantedAuthority;
        import org.springframework.security.core.userdetails.UserDetails;

        import java.util.Collection;
        import java.util.List;

@Entity
@Table(name = "restaurant_owner")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantOwner implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    private String email;

    @Column(nullable = false,unique = true)
    private String phone;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role = Role.RESTAURANT_OWNER;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RestaurantOwnerStatus restaurantOwnerStatus = RestaurantOwnerStatus.ACTIVE;


    @OneToOne(mappedBy = "restaurantOwner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private Restaurant restaurant;

    @OneToMany(mappedBy = "restaurantOwner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("restaurantOwner")
    private List<Orders> orders;


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
