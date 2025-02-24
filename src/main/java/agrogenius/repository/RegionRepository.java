package agrogenius.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import agrogenius.controller.entity.Region;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {
    Region findByRegionName(String regionName);  
}
