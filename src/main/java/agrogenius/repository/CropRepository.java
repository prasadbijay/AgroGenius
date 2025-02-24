package agrogenius.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import agrogenius.controller.entity.Crop;

@Repository
public interface CropRepository extends JpaRepository<Crop, Long> {
    Crop findByCropName(String cropName); 
}
