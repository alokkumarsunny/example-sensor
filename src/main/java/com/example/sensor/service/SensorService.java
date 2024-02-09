package com.example.sensor.service;

import com.example.sensor.deo.SensorModelRepository;
import com.example.sensor.model.SensorModel;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SensorService {

    private final SensorModelRepository sensorRepository;

    public SensorService(SensorModelRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    public List<SensorModel> getAllSensors() {
        return sensorRepository.findAll();
    }
}
