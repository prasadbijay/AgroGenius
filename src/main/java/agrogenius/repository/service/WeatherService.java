package agrogenius.repository.service;

import agrogenius.controller.entity.WeatherData;
import java.util.List;
import java.util.Optional;

public interface WeatherService {

    WeatherData saveWeatherData(WeatherData weatherData);  
    Optional<WeatherData> getWeatherDataById(Long id);  
    List<WeatherData> getWeatherDataBySoilType(String soilType);  // Updated to return List<WeatherData>
    List<WeatherData> getAllWeatherData();  
    void deleteWeatherData(Long id);
    WeatherData updateWeatherData(WeatherData weatherData);
}
