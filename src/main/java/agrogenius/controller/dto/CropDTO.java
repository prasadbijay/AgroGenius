package agrogenius.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CropDTO {
   
    private Long id;

    private String cropName;
    private String otherCrop;

    private WeatherDataDTO weatherData;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCropName() {
		return cropName;
	}

	public void setCropName(String cropName) {
		this.cropName = cropName;
	}

	public String getOtherCrop() {
		return otherCrop;
	}

	public void setOtherCrop(String otherCrop) {
		this.otherCrop = otherCrop;
	}

	public WeatherDataDTO getWeatherData() {
		return weatherData;
	}

	public void setWeatherData(WeatherDataDTO weatherData) {
		this.weatherData = weatherData;
	}

	public CropDTO() {

	}

	public CropDTO(Long id, String cropName, String otherCrop, WeatherDataDTO weatherData) {
		super();
		this.id = id;
		this.cropName = cropName;
		this.otherCrop = otherCrop;
		this.weatherData = weatherData;
	}

}