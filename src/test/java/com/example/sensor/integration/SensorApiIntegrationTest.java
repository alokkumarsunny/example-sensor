package com.example.sensor.integration;

import com.example.sensor.model.SensorModel;
import com.example.sensor.service.SensorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class SensorApiIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SensorService sensorService;

    @BeforeEach
    public void setUp() {
        // You may need to initialize some test data or perform setup tasks here
    }

    @Test
    @DisplayName("Test resister data with valid data")
    public void testRegisterSensorData() throws Exception {
        SensorModel sensorModel = new SensorModel();
        sensorModel.setSensorId("TestSensor");
        sensorModel.setTempValue(25.0);
        sensorModel.setTempUnit("Celsius");
        sensorModel.setHumidityValue(50.0);
        sensorModel.setHumidityUnit("Percentage");
        sensorModel.setInsertionTime(LocalDateTime.now());
        when(sensorService.saveSensorData(sensorModel)).thenReturn(sensorModel);

        mockMvc.perform(MockMvcRequestBuilders.post("/registerSensorData")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(sensorModel)))
                .andExpect(status().isCreated());
    }

    @ParameterizedTest
    @CsvSource({
            " , 25.0, Celsius, 50.0, Percentage, 2024-02-08T12:00:00",
            "12898, , Fahrenheit, 60.0, Percentage, 2024-02-08T13:00:00",
            "34899, 28.0, Celsius, , Percentage, 2024-02-08T14:00:00",
    })
    @DisplayName("Test resister data with invalid data")
    public void testRegisterSensorData_ValidData(String sensorId, Double tempValue, String tempUnit, Double humidityValue, String humidityUnit, LocalDateTime insertionTime) throws Exception {
        SensorModel sensorModel = new SensorModel();
        sensorModel.setSensorId(sensorId);
        if (tempValue != null) {
            sensorModel.setTempValue(tempValue);
        }
        sensorModel.setTempUnit(tempUnit);
        if (humidityValue != null) {
            sensorModel.setHumidityValue(humidityValue);
        }
        sensorModel.setHumidityUnit(humidityUnit);
        sensorModel.setInsertionTime(insertionTime);
        mockMvc.perform(MockMvcRequestBuilders.post("/registerSensorData")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(sensorModel)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testGetAverageTempForAllSensorsInRange() throws Exception {
        // dummy data
        double dummyAverageValue = 25.0;

        LocalDate startDate = LocalDate.of(2024, 2, 9);
        LocalDate endDate = LocalDate.of(2024, 2, 10);
        when(sensorService.getAverageTemperatureInRange(startDate, endDate)).thenReturn(dummyAverageValue);

        //verify
        mockMvc.perform(MockMvcRequestBuilders.get("/sensors/average/temperature")
                        .param("startDate", startDate.toString())
                        .param("endDate", endDate.toString()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(Double.toString(dummyAverageValue)));
    }

    @Test
    public void testGetAverageTempForSensorsInRange() throws Exception {
        // dummy data
        double dummyAverageValue = 25.0;

        LocalDate startDate = LocalDate.of(2024, 2, 9);
        LocalDate endDate = LocalDate.of(2024, 2, 10);
        String sensorID = "98789";
        when(sensorService.getAverageTemperatureForSensorInRange(sensorID,startDate, endDate)).thenReturn(dummyAverageValue);

        //verify
        mockMvc.perform(MockMvcRequestBuilders.get("/sensors/{0}/average/temperature", sensorID)
                        .param("startDate", startDate.toString())
                        .param("endDate", endDate.toString()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(Double.toString(dummyAverageValue)));
    }

    @Test
    public void testGetAverageHumidityForAllSensorsInRange() throws Exception {
        // dummy data
        double dummyAverageValue = 50;

        LocalDate startDate = LocalDate.of(2024, 2, 9);
        LocalDate endDate = LocalDate.of(2024, 2, 10);
        when(sensorService.getAverageHumidityInRange(startDate, endDate)).thenReturn(dummyAverageValue);

        //verify
        mockMvc.perform(MockMvcRequestBuilders.get("/sensors/average/humidity")
                        .param("startDate", startDate.toString())
                        .param("endDate", endDate.toString()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(Double.toString(dummyAverageValue)));
    }

    @Test
    public void testGetAverageHumidityForSensorsInRange() throws Exception {
        // dummy data
        double dummyAverageValue = 50;

        LocalDate startDate = LocalDate.of(2024, 2, 9);
        LocalDate endDate = LocalDate.of(2024, 2, 10);
        String sensorID = "98789";
        when(sensorService.getAverageHumidityForSensorInRange(sensorID, startDate, endDate)).thenReturn(dummyAverageValue);

        //verify
        mockMvc.perform(MockMvcRequestBuilders.get("/sensors/{0}/average/humidity",sensorID)
                        .param("startDate", startDate.toString())
                        .param("endDate", endDate.toString()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(Double.toString(dummyAverageValue)));
    }



    @Test
    @DisplayName("Invalid case for calculation of avg temperature")
    public void testGetAverageTemperatureForAllSensorsInRange() throws Exception {
        //dummy value
        String errorMessasge = "Sample error message";

        LocalDate startDate = LocalDate.of(1992, 1, 1);
        LocalDate endDate = LocalDate.of(1992, 12, 31);

        when(sensorService.getAverageTemperatureInRange(startDate, endDate)).thenThrow(new IllegalStateException(errorMessasge));


        mockMvc.perform(MockMvcRequestBuilders.get("/sensors/average/temperature")
                        .param("startDate", startDate.toString())
                        .param("endDate", endDate.toString()))
                .andExpect(status().isConflict())
                .andExpect(content().string(containsString(errorMessasge)));
    }

    @Test
    @DisplayName("Invalid case for calculation of avg Humidity")
    public void testGetAverageHumidityForAllSensorsInRangeInvalidData() throws Exception {
        //dummy value
        String errorMessasge = "Sample error message";

        LocalDate startDate = LocalDate.of(1992, 1, 1);
        LocalDate endDate = LocalDate.of(1992, 12, 31);

        when(sensorService.getAverageHumidityInRange(startDate, endDate)).thenThrow(new IllegalStateException(errorMessasge));

        mockMvc.perform(MockMvcRequestBuilders.get("/sensors/average/humidity")
                        .param("startDate", startDate.toString())
                        .param("endDate", endDate.toString()))
                .andExpect(status().isConflict())
                .andExpect(content().string(containsString(errorMessasge)));
    }


    // Utility method to convert object to JSON string
    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
