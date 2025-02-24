package agrogenius.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import agrogenius.controller.entity.WeatherData;

@Repository
public interface WeatherRepository extends JpaRepository<WeatherData, Long> { 
    List<WeatherData> findBySoilType(String soilType);
    List<WeatherData> findByWeatherConditionAndSoilTypeAndTemperatureBetween(
        String weatherCondition, String soilType, Double minTemperature, Double maxTemperature);
}

