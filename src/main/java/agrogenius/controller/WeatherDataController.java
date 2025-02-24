package agrogenius.controller;

import agrogenius.controller.entity.WeatherData;
import agrogenius.repository.service.WeatherService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/weather")
public class WeatherDataController {

    private final WeatherService weatherService;

    @Autowired
    public WeatherDataController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @PostMapping
    public ResponseEntity<WeatherData> createWeatherData(@Valid @RequestBody WeatherData weatherData) {
        WeatherData savedWeatherData = weatherService.saveWeatherData(weatherData);
        return new ResponseEntity<>(savedWeatherData, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WeatherData> getWeatherDataById(@PathVariable Long id) {
        Optional<WeatherData> weatherData = weatherService.getWeatherDataById(id);
        return weatherData.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/soil/{soilType}")
    public ResponseEntity<List<WeatherData>> getWeatherDataBySoilType(@PathVariable String soilType) {
        List<WeatherData> weatherDataList = weatherService.getWeatherDataBySoilType(soilType);
        return weatherDataList.isEmpty() 
            ? ResponseEntity.notFound().build() 
            : ResponseEntity.ok(weatherDataList);
    }

    @GetMapping
    public ResponseEntity<List<WeatherData>> getAllWeatherData() {
        List<WeatherData> weatherDataList = weatherService.getAllWeatherData();
        return ResponseEntity.ok(weatherDataList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWeatherData(@PathVariable Long id) {
        weatherService.deleteWeatherData(id);
        return ResponseEntity.noContent().build();
    }
}
