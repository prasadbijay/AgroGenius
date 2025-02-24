package agrogenius.repository.service.impl;

import agrogenius.controller.entity.Crop;
import agrogenius.repository.CropRepository;
import agrogenius.repository.service.CropService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service 
public class CropServiceImpl implements CropService {

    private final CropRepository cropRepository;

    @Autowired  
    public CropServiceImpl(CropRepository cropRepository) {
        this.cropRepository = cropRepository;
    }
    @Override
    public Crop saveCrop(Crop crop) {
        return cropRepository.save(crop);  
    }
    @Override
    public Optional<Crop> getCropById(Long cropId) {
        return cropRepository.findById(cropId);  
    }
    @Override
    public Crop getCropByName(String cropName) {
        return cropRepository.findByCropName(cropName);  
    }
    @Override
    public List<Crop> getAllCrops() {
        return cropRepository.findAll();  
    }
    @Override
    public void deleteCrop(Long cropId) {
        cropRepository.deleteById(cropId);  
    }

	@Override
	public Crop updateCrop(String cropName) {
		// TODO Auto-generated method stub
		return null;
	}
}
