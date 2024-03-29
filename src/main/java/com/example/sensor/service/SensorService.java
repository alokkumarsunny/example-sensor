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
        Double averageTemperature =  sensorRepository.calculateAverageTempValueByInsertionTimeBetween(startDate.atStartOfDay(),endDate.atStartOfDay());
        if (averageTemperature == null) {
            throw new IllegalStateException("Unable to calculate average temperature for the given date range");
        }
        return averageTemperature;
    }

    public Double getAverageHumidityInRange(LocalDate startDate, LocalDate endDate){
        Double averageHumidity = sensorRepository.calculateAverageHumidityValueByInsertionTimeBetween(startDate.atStartOfDay(),endDate.atStartOfDay());
        if (averageHumidity == null) {
            throw new IllegalStateException("Unable to calculate average Humidity for the given date range");
        }
        return averageHumidity;

    }

    public Double getAverageTemperatureForSensorInRange(String sensorId, LocalDate startDate, LocalDate endDate){
        Double averageTemperature = sensorRepository.calculateAverageTempValueBySensorIdAndInsertionTimeBetween(sensorId,startDate.atStartOfDay(),endDate.atStartOfDay());
        if (averageTemperature == null) {
            throw new IllegalStateException("Unable to calculate average temperature for the given sensor in given date range");
        }
        return averageTemperature;
    }

    public Double getAverageHumidityForSensorInRange(String sensorId, LocalDate startDate, LocalDate endDate){
        Double averageHumidity = sensorRepository.calculateAverageHumidityValueBySensorIdAndInsertionTimeBetween(sensorId,startDate.atStartOfDay(),endDate.atStartOfDay());
        if (averageHumidity == null) {
            throw new IllegalStateException("Unable to calculate average Humidity for the given sensor in given date range");
        }
        return averageHumidity;
    }

}
