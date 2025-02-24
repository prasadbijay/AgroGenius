package agrogenius.repository.service.impl;

import agrogenius.controller.entity.Crop;
import agrogenius.controller.entity.Recommendation;
import agrogenius.controller.entity.WeatherData;
import agrogenius.repository.RecommendationRepository;
import agrogenius.repository.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecommendationServiceImpl implements RecommendationService {

    private static final Logger logger = LoggerFactory.getLogger(RecommendationServiceImpl.class);

    private final RecommendationRepository recommendationRepository;

    @Autowired
    public RecommendationServiceImpl(RecommendationRepository recommendationRepository) {
        this.recommendationRepository = recommendationRepository;
    }

    @Override
    public Recommendation saveRecommendation(Recommendation recommendation) {
        if (recommendation == null || recommendation.getWeatherData() == null) {
            logger.warn("❌ Invalid recommendation data. WeatherData is null.");
            return null;
        }

        WeatherData weatherData = recommendation.getWeatherData();

        // 🔍 Check if a recommendation already exists for the given weather conditions
        Optional<Recommendation> foundRecommendation = recommendationRepository
                .findFirstByWeatherData_SoilTypeIgnoreCaseAndWeatherData_WeatherConditionIgnoreCaseAndWeatherData_TemperatureLessThanEqualAndWeatherData_RangeTemperatureGreaterThanEqual(
                        weatherData.getSoilType(),
                        weatherData.getWeatherCondition(),
                        weatherData.getTemperature(),
                        weatherData.getRangeTemperature()
                );

        if (foundRecommendation.isPresent()) {
            Recommendation existingRecommendation = foundRecommendation.get();
            logger.info("✅ Existing recommendation found: Crop(s) = {}",
                    existingRecommendation.getCrops().stream().map(Crop::getCropName).collect(Collectors.toList()));
            return existingRecommendation;
        } else {
            logger.info("🆕 No matching recommendation found. Saving new recommendation.");
            return recommendationRepository.save(recommendation);
        }
    }

    @Override
    public Optional<Recommendation> getRecommendationById(Long id) {
        return recommendationRepository.findById(id);
    }

    @Override
    public List<Recommendation> getAllRecommendations() {
        return recommendationRepository.findAll();
    }

    @Override
    public void deleteRecommendation(Long id) {
        if (!recommendationRepository.existsById(id)) {
            logger.warn("⚠️ Recommendation with ID {} not found. Cannot delete.", id);
            return;
        }
        recommendationRepository.deleteById(id);
        logger.info("🗑️ Deleted recommendation with ID {}", id);
    }

    @Override
    public Recommendation updateRecommendation(Recommendation recommendation) {
        if (recommendation == null || recommendation.getId() == null) {
            logger.warn("❌ Invalid update request: recommendation or ID is null.");
            return null;
        }

        return recommendationRepository.findById(recommendation.getId())
                .map(existingRecommendation -> {
                    existingRecommendation.setWeatherData(recommendation.getWeatherData());
                    existingRecommendation.setCrops(recommendation.getCrops());
                    logger.info("✏️ Updated recommendation with ID {}", recommendation.getId());
                    return recommendationRepository.save(existingRecommendation);
                })
                .orElse(null);
    }

    // ✅ Updated getCropsByWeather() with better logging and case-insensitive query
    @Override
    public List<String> getCropsByWeather(double temperature, String soilType, String weatherCondition) {
        logger.info("🔎 Fetching crops for SoilType: '{}', WeatherCondition: '{}', Temperature: {}", 
                    soilType, weatherCondition, temperature);

        Optional<Recommendation> recommendation = recommendationRepository
                .findFirstByWeatherData_SoilTypeIgnoreCaseAndWeatherData_WeatherConditionIgnoreCaseAndWeatherData_TemperatureLessThanEqualAndWeatherData_RangeTemperatureGreaterThanEqual(
                        soilType.trim(), 
                        weatherCondition.trim(), 
                        temperature, 
                        temperature
                );

        if (recommendation.isPresent() && recommendation.get().getCrops() != null) {
            List<String> cropNames = recommendation.get().getCrops().stream()
                    .map(Crop::getCropName)
                    .collect(Collectors.toList());
            logger.info("🌱 Recommended Crops: {}", cropNames);
            return cropNames;
        }

        logger.warn("⚠️ No crops found for the given weather conditions.");
        return List.of(); // ✅ Return an empty list instead of null
    }
}
