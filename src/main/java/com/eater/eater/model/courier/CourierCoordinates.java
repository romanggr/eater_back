package com.eater.eater.model.courier;

import com.eater.eater.model.courier.Courier;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "courier_coordinates")
@Getter
@Setter
public class CourierCoordinates {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double latitude;
    private Double longitude;
    private LocalDateTime lastUpdate;

    @OneToOne
    @JoinColumn(name = "courier_id", referencedColumnName = "id")
    @JsonIgnoreProperties("courier")
    private Courier courier;
}
