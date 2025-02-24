package agrogenius.repository.service;

import java.util.List;
import java.util.Optional;

import agrogenius.controller.entity.Admin;
import agrogenius.controller.entity.Crop;
import agrogenius.controller.entity.Recommendation;
import agrogenius.controller.entity.Region;
import agrogenius.controller.entity.User;
import agrogenius.controller.entity.WeatherData;

public interface AdminService {

	Admin saveAdmin(Admin admin);
	Optional<Admin> getAdminById(Long adminId);
	Admin getAdminByName(String adminName);
	List <Admin> getAllAdmin();
	Admin updateAdmin(Admin admin);
	void deleteAdmin (Long adminId);
	
	Crop saveCrop (Crop crop);
	Optional <Crop> getCropById(Long cropId);
	Crop getCropByName(String cropName);
	List <Crop> getAllCrop();
	void deleteCrop(Long cropId);
	
    Recommendation saveRecommendation(Recommendation recommendation);  
    Optional<Recommendation> getRecommendationById(Long id); 
    List<Recommendation> getAllRecommendations(); 
    void deleteRecommendation(Long id); 
    

    Region saveRegion(Region region);  
    Optional<Region> getRegionById(Long id); 
    Region getRegionByName(String regionName);  
    List<Region> getAllRegions();  
    void deleteRegion(Long id);
    
  
    User saveUser(User user);  
    Optional<User> getUserById(Long id);  
    User getUserByUserName(String userName);  
    User getUserByEmail(String email);  
    List<User> getAllUsers();  
    void deleteUser(Long id);
    
   
    WeatherData saveWeatherData(WeatherData weatherData);  
    Optional<WeatherData> getWeatherDataById(Long id);  
    WeatherData getWeatherDataBySoilType(String soilType);  
    List<WeatherData> getAllWeatherData();  
    void deleteWeatherData(Long id);
	
	
}
