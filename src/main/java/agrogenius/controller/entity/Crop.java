package agrogenius.controller.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "crops")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Crop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message ="Crop name can't be blank")
    @Size(min =5 , max=30 ,message="Please enter a valid crop name")
    @Column(nullable=false)
    private String cropName;
    
    @Size(min=5, max=30, message="Please enter a valid other crop name")
    @Column(nullable=true)
    private String otherCrop;

    @ManyToOne
    @JoinColumn(name = "weather_data_id") 
    private WeatherData weatherData;

    @ManyToOne
    @JoinColumn(name = "recommendation_id") // Foreign key for Recommendation
    private Recommendation recommendation;

    

    public Crop() {
		
		// TODO Auto-generated constructor stub
	}

	public Crop(String cropName, String otherCrop) {
        this.cropName = cropName;
        this.otherCrop = otherCrop;
    }

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

	public WeatherData getWeatherData() {
		return weatherData;
	}

	public void setWeatherData(WeatherData weatherData) {
		this.weatherData = weatherData;
	}

}

