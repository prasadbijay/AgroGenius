package agrogenius.controller.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "weather_data")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WeatherData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Positive(message = "Temperature must be positive")
    @Column(nullable = false)
    private double temperature; 
    
    @Positive(message = "Range temperature must be positive")
    @Column(nullable = false)
    private double rangeTemperature; 
    
    @NotBlank(message = "Soil type can't be blank")
    @Size(min = 3, max = 10, message = "Please enter a valid soil type")
    @Pattern(regexp = "(?i)Clay|Sandy|Loamy", message = "Please enter a valid soil type (Clay, Sandy, Loamy)")
    @Column(nullable = false)
    private String soilType;

    @NotBlank(message = "Weather condition can't be blank")
    @Size(min = 3, max = 10, message = "Please enter a valid weather condition")
    @Pattern(regexp = "(?i)Sunny|Rainy|Cloudy|Stormy", message = "Please enter a valid weather condition (Sunny, Rainy, Cloudy, Stormy)")
    @Column(nullable = false)
    private String weatherCondition;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public double getTemperature() {
		return temperature;
	}
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}
	public double getRangeTemperature() {
		return rangeTemperature;
	}
	public void setRangeTemperature(double rangeTemperature) {
		this.rangeTemperature = rangeTemperature;
	}
	public String getSoilType() {
		return soilType;
	}
	public void setSoilType(String soilType) {
		this.soilType = soilType;
	}
	public String getWeatherCondition() {
		return weatherCondition;
	}
	public void setWeatherCondition(String weatherCondition) {
		this.weatherCondition = weatherCondition;
	}
    
}
