package agrogenius.repository.service;

import agrogenius.controller.entity.Recommendation;
import java.util.List;
import java.util.Optional;

public interface RecommendationService {
    Recommendation saveRecommendation(Recommendation recommendation);
    Optional<Recommendation> getRecommendationById(Long id);
    List<Recommendation> getAllRecommendations();
    void deleteRecommendation(Long id);
    Recommendation updateRecommendation(Recommendation recommendation);

    // ✅ Fetch crops based on weather conditions
    List<String> getCropsByWeather(double temperature, String soilType, String weatherCondition);
}
