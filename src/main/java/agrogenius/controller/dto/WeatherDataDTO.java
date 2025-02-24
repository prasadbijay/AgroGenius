package agrogenius.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WeatherDataDTO {
    private Long id;

    private double temperature;          
    private double rangeTemperature;     
    private String soilType;
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
	public WeatherDataDTO(Long id, double temperature, double rangeTemperature, String soilType) {
		super();
		this.id = id;
		this.temperature = temperature;
		this.rangeTemperature = rangeTemperature;
		this.soilType = soilType;
	}
	public WeatherDataDTO() {
		
	}
    
}