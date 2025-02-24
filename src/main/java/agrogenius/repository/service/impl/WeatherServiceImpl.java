package agrogenius.repository.service.impl;

import agrogenius.controller.entity.WeatherData;
import agrogenius.exception.ResourceNotFoundException;
import agrogenius.repository.WeatherRepository;
import agrogenius.repository.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WeatherServiceImpl implements WeatherService {

    private final WeatherRepository weatherRepository;

    @Autowired
    public WeatherServiceImpl(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    @Override
    public WeatherData saveWeatherData(WeatherData weatherData) {
        // Save the weather data to the database
        return weatherRepository.save(weatherData);
    }

    @Override
    public Optional<WeatherData> getWeatherDataById(Long id) {
        // Retrieve weather data by ID, returning an Optional
        return weatherRepository.findById(id);
    }

    @Override
    public List<WeatherData> getWeatherDataBySoilType(String soilType) {
        // Query the repository by soil type and return the result as a list
        List<WeatherData> weatherDataList = weatherRepository.findBySoilType(soilType);
        if (weatherDataList.isEmpty()) {
            throw new ResourceNotFoundException("No weather data found for soil type: " + soilType);
        }
        return weatherDataList;
    }

    @Override
    public List<WeatherData> getAllWeatherData() {
        // Retrieve all weather data from the repository
        return weatherRepository.findAll();
    }

    @Override
    public void deleteWeatherData(Long id) {
        // Delete weather data by ID if it exists, otherwise throw an exception
        if (weatherRepository.existsById(id)) {
            weatherRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Weather data not found for id: " + id);
        }
    }

    @Override
    public WeatherData updateWeatherData(WeatherData weatherData) {
        // Check if the weather data exists, and update if so
        if (weatherRepository.existsById(weatherData.getId())) {
            return weatherRepository.save(weatherData);
        }
        throw new ResourceNotFoundException("Weather data not found for id: " + weatherData.getId());
    }
}
