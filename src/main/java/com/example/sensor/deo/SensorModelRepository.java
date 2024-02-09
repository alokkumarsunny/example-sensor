package com.example.sensor.deo;

import com.example.sensor.model.SensorModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorModelRepository extends JpaRepository<SensorModel, Long> {
}
