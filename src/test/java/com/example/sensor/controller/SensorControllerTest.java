package com.example.sensor.controller;
import com.example.sensor.model.SensorModel;
import com.example.sensor.service.SensorService;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class SensorControllerTest {

    @Mock
    private SensorService sensorService;

    @InjectMocks
    private SensorController sensorController;

    @Test
    @DisplayName("Positive case for getting Average Temperature")
    public void testGetAverageMetricForAllSensorsInRange() {
        // Arrange
        String metric = "temperature";
        LocalDate startDate = LocalDate.of(2022, 1, 1);
        LocalDate endDate = LocalDate.of(2022, 1, 10);
        double expectedAverageValue = 25.5;

        when(sensorService.getAverageTemperatureInRange(startDate, endDate))
                .thenReturn(expectedAverageValue);

        ResponseEntity<Double> response = sensorController.getAverageMetricForAllSensorsInRange(metric, startDate, endDate);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedAverageValue, response.getBody());
    }

    @Test
    @DisplayName("Positive case for getting Average Temperature")
    public void testRegisterSensorData() {
        // Arrange
        String metric = "temperature";
        LocalDate startDate = LocalDate.of(2022, 1, 1);
        LocalDate endDate = LocalDate.of(2022, 1, 10);
        double expectedAverageValue = 25.5;

        when(sensorService.getAverageTemperatureInRange(startDate, endDate))
                .thenReturn(expectedAverageValue);

        ResponseEntity<Double> response = sensorController.getAverageMetricForAllSensorsInRange(metric, startDate, endDate);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedAverageValue, response.getBody());
    }

    @Test
    void createSensor_ValidSensorModel_ReturnsCreatedResponse() {
        SensorModel sensorModel = new SensorModel();
        sensorModel.setSensorId("TestSensor");
        sensorModel.setTempValue(25.5);
        sensorModel.setTempUnit("Celsius");
        sensorModel.setHumidityValue(50.0);
        sensorModel.setHumidityUnit("Percentage");

        when(sensorService.saveSensorData(any(SensorModel.class))).thenReturn(sensorModel);

        ResponseEntity<SensorModel> response = sensorController.createSensor(sensorModel);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(sensorModel, response.getBody());
    }
}
