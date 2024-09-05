package com.eater.eater.model.restaurantOwner;

import com.eater.eater.model.courier.Courier;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "restaurant")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String address;
    private Double latitude;
    private Double longitude;

    private boolean isOpenFrom;
    private boolean isOpenTo;

    @OneToOne
    @JoinColumn(name = "restaurant_owner_id", referencedColumnName = "id")
    @JsonIgnoreProperties("restaurant")
    private RestaurantOwner restaurantOwner;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("restaurant")
    private List<RestaurantDish> restaurantDishes;
}
