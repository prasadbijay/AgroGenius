package agrogenius.controller;

import agrogenius.controller.entity.Region;
import agrogenius.repository.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/regions")
public class RegionController {

    private final RegionService regionService;

    @Autowired
    public RegionController(RegionService regionService) {
        this.regionService = regionService;
       
    }

    @PostMapping
    public ResponseEntity<Region> createRegion(@RequestBody Region region) {
        try {
            // Save the region directly
            Region savedRegion = regionService.saveRegion(region);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedRegion);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Region> getRegionById(@PathVariable Long id) {
        Optional<Region> region = regionService.getRegionById(id);
        return region.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/name/{regionName}")
    public ResponseEntity<Region> getRegionByName(@PathVariable String regionName) {
        Region region = regionService.getRegionByName(regionName);
        return region != null ? ResponseEntity.ok(region) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Region>> getAllRegions() {
        List<Region> regions = regionService.getAllRegions();
        return ResponseEntity.ok(regions);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRegion(@PathVariable Long id) {
        regionService.deleteRegion(id);
        return ResponseEntity.noContent().build();
    }
}
