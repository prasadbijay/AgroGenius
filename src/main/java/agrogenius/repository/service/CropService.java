package agrogenius.repository.service;

import java.util.Optional;
import java.util.List;
import agrogenius.controller.entity.Crop;

public interface CropService {
	Crop saveCrop (Crop crop); 
    Optional<Crop> getCropById(Long cropId);  
    Crop getCropByName(String cropName);  
    List<Crop> getAllCrops();  
    void deleteCrop(Long cropId); 
	Crop updateCrop(String crop);
}
