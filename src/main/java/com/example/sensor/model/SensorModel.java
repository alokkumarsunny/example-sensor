package com.example.sensor.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;


@Entity
@Data
public class SensorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sensorId;
    private Double tempValue;
    private String tempUnit;
    private Double humidityValue;
    private String humidityUnit;
    private LocalDateTime insertionTime;

    @PrePersist
    public void prePersist() {
        this.insertionTime = LocalDateTime.now();
    }
}
