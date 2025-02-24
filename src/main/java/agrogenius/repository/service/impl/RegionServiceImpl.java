package agrogenius.repository.service.impl;

import agrogenius.controller.entity.Region;
import agrogenius.repository.RegionRepository;
import agrogenius.repository.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegionServiceImpl implements RegionService {

    private final RegionRepository regionRepository;

    @Autowired
    public RegionServiceImpl(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }


    @Override
    public Optional<Region> getRegionById(Long id) {
        return regionRepository.findById(id);
    }

    @Override
    public Region getRegionByName(String regionName) {
        return regionRepository.findByRegionName(regionName);
    }

    @Override
    public List<Region> getAllRegions() {
        return regionRepository.findAll();
    }

    @Override
    public void deleteRegion(Long id) {
        regionRepository.deleteById(id);
    }

	@Override
	public Region saveRegion(Region region) {
		// TODO Auto-generated method stub
		return regionRepository.save(region);
	}

	@Override
	public Region updateRegion(Region region) {
	    Optional<Region> existingRegion = regionRepository.findById(region.getId());
	    
	    if (existingRegion.isPresent()) {   
	        Region updatedRegion = existingRegion.get();
	        updatedRegion.setRegionName(region.getRegionName());
	        updatedRegion.setState(region.getState());
	        return regionRepository.save(updatedRegion);
	    } else {    
	        return null;
	    }
	}

}
