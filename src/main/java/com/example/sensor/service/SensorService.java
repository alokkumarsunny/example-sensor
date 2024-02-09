package com.example.sensor.service;

import com.example.sensor.deo.SensorModelRepository;
import com.example.sensor.model.SensorModel;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class SensorService {

    private final SensorModelRepository sensorRepository;

    public SensorService(SensorModelRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    public SensorModel saveSensorData(SensorModel sensorModel){
        return sensorRepository.save(sensorModel);
    }
    public Double getAverageTemperatureInRange(LocalDate startDate, LocalDate endDate){
        return sensorRepository.calculateAverageTempValueByInsertionTimeBetween(startDate.atStartOfDay(),endDate.atStartOfDay());
    }

    public Double getAverageHumidityInRange(LocalDate startDate, LocalDate endDate){
        return sensorRepository.calculateAverageHumidityValueByInsertionTimeBetween(startDate.atStartOfDay(),endDate.atStartOfDay());
    }

    public Double getAverageTemperatureForSensorInRange(String sensorId, LocalDate startDate, LocalDate endDate){
        return sensorRepository.calculateAverageTempValueBySensorIdAndInsertionTimeBetween(sensorId,startDate.atStartOfDay(),endDate.atStartOfDay());
    }

    public Double getAverageHumidityForSensorInRange(String sensorId, LocalDate startDate, LocalDate endDate){
        return sensorRepository.calculateAverageHumidityValueBySensorIdAndInsertionTimeBetween(sensorId,startDate.atStartOfDay(),endDate.atStartOfDay());
    }

}
