package com.example.sensor.controller;

import com.example.sensor.model.SensorModel;
import com.example.sensor.service.SensorService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@Validated
public class SensorController {

    private final SensorService sensorService;

    public SensorController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @PostMapping("/registerSensorData")
    public ResponseEntity<SensorModel> createSensor(@Valid @RequestBody SensorModel sensorModel) {
        SensorModel sensorModelResult = sensorService.saveSensorData(sensorModel);

        return new ResponseEntity<>(sensorModelResult, HttpStatus.CREATED);
    }


    @GetMapping("/sensors/average/{metric}")
    public ResponseEntity<Double> getAverageMetricForAllSensorsInRange(
            @PathVariable("metric") String metric,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        double averageValue;
        if ("temperature".equalsIgnoreCase(metric)) {
            averageValue = sensorService.getAverageTemperatureInRange(startDate, endDate);
        } else if ("humidity".equalsIgnoreCase(metric)) {
            averageValue =  sensorService.getAverageHumidityInRange(startDate, endDate);
        } else {
            throw new IllegalArgumentException("Invalid metric type. Supported values are 'temperature' and 'humidity'.");
        }
        return ResponseEntity.ok(averageValue);
    }

    @GetMapping("/sensors/{sensorId}/average/{metric}")
    public ResponseEntity<Double> getAverageMetricForSensorInRange(
            @PathVariable("sensorId") String sensorId,
            @PathVariable("metric") String metric,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        double averageValue;
        if ("temperature".equalsIgnoreCase(metric)) {
            averageValue = sensorService.getAverageTemperatureForSensorInRange(sensorId, startDate, endDate);
        } else if ("humidity".equalsIgnoreCase(metric)) {
            averageValue = sensorService.getAverageHumidityForSensorInRange(sensorId, startDate, endDate);
        } else {
            throw new IllegalArgumentException("Invalid metric type. Supported values are 'temperature' and 'humidity'.");
        }
        return ResponseEntity.ok(averageValue);
    }


}
