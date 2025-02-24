package agrogenius.controller;

import agrogenius.controller.entity.Recommendation;
import agrogenius.repository.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/recommendation")
public class RecommendationController {

    private final RecommendationService recommendationService;

    @Autowired
    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @PostMapping
    public ResponseEntity<Recommendation> createRecommendation(@RequestBody Recommendation recommendation) {
        if (recommendation == null || recommendation.getWeatherData() == null) {
            return ResponseEntity.badRequest().build();
        }
        Recommendation createdRecommendation = recommendationService.saveRecommendation(recommendation);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRecommendation);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recommendation> getRecommendationById(@PathVariable Long id) {
        Optional<Recommendation> recommendation = recommendationService.getRecommendationById(id);
        return recommendation.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Recommendation>> getAllRecommendations() {
        List<Recommendation> recommendations = recommendationService.getAllRecommendations();
        return ResponseEntity.ok(recommendations);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecommendation(@PathVariable Long id) {
        recommendationService.deleteRecommendation(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/crops")
    public ResponseEntity<List<String>> getRecommendedCrops(
            @RequestParam double temperature,
            @RequestParam String soilType,
            @RequestParam String weatherCondition) {

        List<String> recommendedCrops = recommendationService.getCropsByWeather(temperature, soilType, weatherCondition);

        if (recommendedCrops.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(List.of("No recommendations found."));
        }
        return ResponseEntity.ok(recommendedCrops);
    }
}
