package com.example.sensor.controller;

import com.example.sensor.deo.SensorModelRepository;
import com.example.sensor.model.SensorModel;
import com.example.sensor.service.SensorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SensorController {

    private final SensorModelRepository sensorModelRepository;
    private final SensorService sensorService;

    public SensorController(SensorModelRepository sensorModelRepository, SensorService sensorService) {
        this.sensorModelRepository = sensorModelRepository;
        this.sensorService = sensorService;
    }

    @PostMapping("/registerSensorData")
    public SensorModel createSensor(@RequestBody SensorModel sensorModel) {
        return sensorModelRepository.save(sensorModel);
    }

    @GetMapping("/getAllSensor")
    public List<SensorModel> getAllSensors() {
        return sensorService.getAllSensors();
    }
}
