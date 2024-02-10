package com.example.sensor.service;

import com.example.sensor.deo.SensorModelRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SensorServiceTest {

    @Mock
    private SensorModelRepository sensorRepository;

    @InjectMocks
    private SensorService sensorService;

    @Test
    public void testGetAverageTemperatureInRange() {
        // Arrange
        LocalDate startDate = LocalDate.of(2024, 2, 9);
        LocalDate endDate = LocalDate.of(2024, 2, 10);
        double expectedAverageTemperature = 35.5;

        when(sensorRepository.calculateAverageTempValueByInsertionTimeBetween(startDate.atStartOfDay(), endDate.atStartOfDay()))
                .thenReturn(expectedAverageTemperature);

        double actualAverageTemperature = sensorService.getAverageTemperatureInRange(startDate, endDate);

        assertEquals(expectedAverageTemperature, actualAverageTemperature);
    }

    @Test
    public void testGetAverageTemperatureInRangeWhenNoValueReturn() {
        // Arrange
        LocalDate startDate = LocalDate.of(2024, 2, 9);
        LocalDate endDate = LocalDate.of(2024, 2, 10);

        when(sensorRepository.calculateAverageTempValueByInsertionTimeBetween(startDate.atStartOfDay(), endDate.atStartOfDay()))
                .thenReturn(null);

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            sensorService.getAverageTemperatureInRange(startDate, endDate);
        });
        assertEquals("Unable to calculate average temperature for the given date range", exception.getMessage());
    }

    @Test
    public void testGetAverageTemperatureBySensorInRangeWhenNoValueReturn() {
        // Arrange
        LocalDate startDate = LocalDate.of(2024, 2, 9);
        LocalDate endDate = LocalDate.of(2024, 2, 10);
        String sensorID = "98789";

        when(sensorRepository.calculateAverageTempValueBySensorIdAndInsertionTimeBetween(sensorID,startDate.atStartOfDay(), endDate.atStartOfDay()))
                .thenReturn(null);

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            sensorService.getAverageTemperatureForSensorInRange(sensorID,startDate, endDate);
        });
        assertEquals("Unable to calculate average temperature for the given sensor in given date range", exception.getMessage());
    }

    //to do for humidity check

}

