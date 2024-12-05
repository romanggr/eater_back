package com.eater.eater.model.order;

import com.eater.eater.restaurantOwner.model.RestaurantDish;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "order_menu")
@Data
public class OrderMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Orders order;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "restaurant_dish_id", referencedColumnName = "id")
    private RestaurantDish dish;

    private int dishQuantity;
}

