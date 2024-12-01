package com.eater.eater.model.orders;

import com.eater.eater.enums.OrderStatus;
import com.eater.eater.model.client.Client;
import com.eater.eater.model.courier.Courier;
import com.eater.eater.model.restaurantOwner.RestaurantOwner;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double totalPrice;

    private Double restaurantEarnings;

    @Column(nullable = false)
    private Double appEarnings;

    @Column(nullable = false)
    private Double courierEarnings;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    // todo finsih by courier
    private LocalDateTime finishedAt;

    @Column(nullable = false)
    private Integer distance;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(nullable = false)
    private double courierStartLatitude;

    @Column(nullable = false)
    private double courierStartLongitude;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderMenu> orderMenus;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "courier_id", referencedColumnName = "id")
    private Courier courier;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "restaurant_owner_id", referencedColumnName = "id")
    private RestaurantOwner restaurantOwner;
}
