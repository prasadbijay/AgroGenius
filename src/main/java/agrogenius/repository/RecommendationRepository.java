package agrogenius.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import agrogenius.controller.entity.Recommendation;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface RecommendationRepository extends JpaRepository<Recommendation, Long> {
    
    @Query("SELECT r FROM Recommendation r WHERE " +
           "LOWER(r.weatherData.soilType) = LOWER(:soilType) AND " +
           "LOWER(r.weatherData.weatherCondition) = LOWER(:weatherCondition) AND " +
           "r.weatherData.temperature <= :temperature AND " +
           "r.weatherData.rangeTemperature >= :temperature")
    Optional<Recommendation> findRecommendationByWeather(
        @Param("soilType") String soilType, 
        @Param("weatherCondition") String weatherCondition, 
        @Param("temperature") double temperature);

	Optional<Recommendation> findFirstByWeatherData_SoilTypeIgnoreCaseAndWeatherData_WeatherConditionIgnoreCaseAndWeatherData_TemperatureLessThanEqualAndWeatherData_RangeTemperatureGreaterThanEqual(
			String soilType, String weatherCondition, double temperature, double rangeTemperature);
}
