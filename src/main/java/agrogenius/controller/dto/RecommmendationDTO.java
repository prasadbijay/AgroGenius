package agrogenius.controller.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecommmendationDTO {

	    private Long id;

	    private String recommendationText;

	    private CropDTO crop;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getRecommendationText() {
			return recommendationText;
		}

		public void setRecommendationText(String recommendationText) {
			this.recommendationText = recommendationText;
		}

		public CropDTO getCrop() {
			return crop;
		}

		public void setCrop(CropDTO crop) {
			this.crop = crop;
		}

		public RecommmendationDTO() {
			
		}

		public RecommmendationDTO(Long id, String recommendationText, CropDTO crop) {
			super();
			this.id = id;
			this.recommendationText = recommendationText;
			this.crop = crop;
		}
	    
}
