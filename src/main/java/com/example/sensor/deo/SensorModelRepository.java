package com.example.sensor.deo;

import com.example.sensor.model.SensorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface SensorModelRepository extends JpaRepository<SensorModel, Long> {

    @Query("SELECT AVG(s.tempValue) FROM SensorModel s WHERE s.insertionTime BETWEEN :startDate AND :endDate")
    Double calculateAverageTempValueByInsertionTimeBetween(
            LocalDateTime startDate, LocalDateTime endDate);

    @Query("SELECT AVG(s.humidityValue) FROM SensorModel s WHERE s.insertionTime BETWEEN :startDate AND :endDate")
    Double calculateAverageHumidityValueByInsertionTimeBetween(
            LocalDateTime startDate, LocalDateTime endDate);

    @Query("SELECT AVG(s.tempValue) FROM SensorModel s WHERE s.sensorId = :sensorId AND s.insertionTime BETWEEN :startDate AND :endDate")
    Double calculateAverageTempValueBySensorIdAndInsertionTimeBetween(
            String sensorId, LocalDateTime startDate, LocalDateTime endDate);

    @Query("SELECT AVG(s.humidityValue) FROM SensorModel s WHERE s.sensorId = :sensorId AND s.insertionTime BETWEEN :startDate AND :endDate")
    Double calculateAverageHumidityValueBySensorIdAndInsertionTimeBetween(
            String sensorId, LocalDateTime startDate, LocalDateTime endDate);

}
