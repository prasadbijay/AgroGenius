	package agrogenius.controller;
	
	import agrogenius.controller.entity.Crop;
	import agrogenius.repository.service.CropService;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.http.HttpStatus;
	import org.springframework.http.ResponseEntity;
	import org.springframework.web.bind.annotation.*;
	
	import java.util.List;
	import java.util.Optional;
	
	@CrossOrigin(origins = {"http://127.0.0.1:5500", "http://localhost:5500"})
	@RestController
	@RequestMapping("/crops")
	public class CropController {
	
	    private final CropService cropService;
	
	    @Autowired
	    public CropController(CropService cropService) {
	        this.cropService = cropService;
	    }
	
	    @PostMapping
	    public ResponseEntity<Crop> createCrop(@RequestBody Crop crop) {
	        Crop createdCrop = cropService.saveCrop(crop);
	        return new ResponseEntity<>(createdCrop, HttpStatus.CREATED);
	    }

	
	    @GetMapping("/{id}")
	    public ResponseEntity<Crop> getCropById(@PathVariable Long id) {
	        Optional<Crop> crop = cropService.getCropById(id);
	        return crop.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	    }
	
	    @GetMapping("/name/{cropName}")
	    public ResponseEntity<Crop> getCropByName(@PathVariable String cropName) {
	        Crop crop = cropService.getCropByName(cropName);
	        return crop != null ? ResponseEntity.ok(crop) : ResponseEntity.notFound().build();
	    }
	
	    @GetMapping
	    public ResponseEntity<List<Crop>> getAllCrops() {
	        List<Crop> crops = cropService.getAllCrops();
	        return ResponseEntity.ok(crops);
	    }
	
	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteCrop(@PathVariable Long id) {
	        cropService.deleteCrop(id);
	        return ResponseEntity.noContent().build();
	    }
	}
