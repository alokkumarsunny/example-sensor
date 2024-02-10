package com.example.sensor.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class SensorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Sensor id can not be empty or blank")
    private String sensorId;
    @NotNull(message = "Temperature must have some value")
    private Double tempValue;
    @NotBlank(message = "Temperature Unit can not be empty or blank")
    private String tempUnit;
    @NotNull(message = "Humidity must have some value")
    private Double humidityValue;
    @NotBlank(message = "Humidity unit can not be empty or blank")
    private String humidityUnit;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime insertionTime;

    @PrePersist
    public void prePersist() {
        this.insertionTime = LocalDateTime.now();
    }
}
