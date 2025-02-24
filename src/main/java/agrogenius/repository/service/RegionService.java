package agrogenius.repository.service;

import agrogenius.controller.entity.Region;
import java.util.List;
import java.util.Optional;

public interface RegionService {
	Region saveRegion(Region region);
    Optional<Region> getRegionById(Long id); 
    Region getRegionByName(String regionName);  
    List<Region> getAllRegions();  
    void deleteRegion(Long id); 
	Region updateRegion(Region region);
}
