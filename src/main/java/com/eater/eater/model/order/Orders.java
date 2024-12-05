package com.eater.eater.model.order;

import com.eater.eater.client.model.Client;
import com.eater.eater.courier.model.Courier;
import com.eater.eater.enums.OrderStatus;
import com.eater.eater.restaurantOwner.model.RestaurantOwner;
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
