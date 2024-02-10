# Sensor Data API

The Sensor Data API is a RESTful service built with Spring Boot that allows users to register sensor data and retrieve average metrics for temperature and humidity.

## Features

- **Register Sensor Data**: Allows users to register sensor data including temperature and humidity values.
- **Calculate Average Metrics**: Provides endpoints to calculate the average temperature and humidity values for all sensors or a specific sensor within a given date range.

## API Endpoints

- **Register Sensor Data**: `POST /registerSensorData`
    - Request Body: JSON object representing the sensor data.
    - Response: Returns the registered sensor data with HTTP status 201 (Created).

- **Get Average Metric for All Sensors**: `GET /sensors/average/{metric}?startDate={startDate}&endDate={endDate}`
    - Path Parameters:
        - `{metric}`: Specifies the metric type (temperature or humidity).
    - Query Parameters:
        - `startDate`: Start date of the date range (ISO format).
        - `endDate`: End date of the date range (ISO format).
    - Response: Returns the average metric value for all sensors within the specified date range.

- **Get Average Metric for Sensor**: `GET /sensors/{sensorId}/average/{metric}?startDate={startDate}&endDate={endDate}`
    - Path Parameters:
        - `{sensorId}`: Specifies the ID of the sensor.
        - `{metric}`: Specifies the metric type (temperature or humidity).
    - Query Parameters:
        - `startDate`: Start date of the date range (ISO format).
        - `endDate`: End date of the date range (ISO format).
    - Response: Returns the average metric value for a specific sensor within the specified date range.

## Running the Application

1. **Prerequisites**:
    - Java Development Kit (JDK) 11 or higher
    - Maven

2. **Clone the Repository**:
   ```bash
   git clone https://github.com/alokkumarsunny/example-sensor.git
   ```

3. **Navigate to the Project Directory**:
   ```bash
   cd example-sensor
   ```

4. **Build the Project**:
   ```bash
   mvn clean package
   ```

5. **Run the Application**:
   ```bash
   java -jar target/sensor-0.0.1-SNAPSHOT.jar
   ```

6. **Access the API**:
   You can now access the API at `http://localhost:8080/api`.

## Documentation

API documentation can be found using Swagger UI:
- **Swagger UI**: [http://localhost:8080/api/swagger-ui.html](http://localhost:8080/api/swagger-ui.html)

- **Postman Collection** Sensor-data.postman_collection.json can be found in root folder.

## Testing

Unit and integration tests are included in the `src/test` directory. You can run the tests using Maven:
```bash
mvn test
```

## Contributing

Alok Kumar