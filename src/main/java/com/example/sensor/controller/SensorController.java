package com.example.sensor.controller;

import com.example.sensor.model.SensorModel;
import com.example.sensor.service.SensorService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
public class SensorController {

    private final SensorService sensorService;

    public SensorController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @PostMapping("/registerSensorData")
    public SensorModel createSensor(@RequestBody SensorModel sensorModel) {
        return sensorService.saveSensorData(sensorModel);
    }


    @GetMapping("/sensors/average/{metric}")
    public Double getAverageMetricForAllSensorsInRange(
            @PathVariable("metric") String metric,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        if ("temperature".equalsIgnoreCase(metric)) {
            return sensorService.getAverageTemperatureInRange(startDate, endDate);
        } else if ("humidity".equalsIgnoreCase(metric)) {
            return sensorService.getAverageHumidityInRange(startDate, endDate);
        } else {
            throw new IllegalArgumentException("Invalid metric type. Supported values are 'temperature' and 'humidity'.");
        }
    }

    @GetMapping("/sensors/{sensorId}/average/{metric}")
    public Double getAverageMetricForSensorInRange(
            @PathVariable("sensorId") String sensorId,
            @PathVariable("metric") String metric,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        if ("temperature".equalsIgnoreCase(metric)) {
            return sensorService.getAverageTemperatureForSensorInRange(sensorId, startDate, endDate);
        } else if ("humidity".equalsIgnoreCase(metric)) {
            return sensorService.getAverageHumidityForSensorInRange(sensorId, startDate, endDate);
        } else {
            throw new IllegalArgumentException("Invalid metric type. Supported values are 'temperature' and 'humidity'.");
        }
    }


}
