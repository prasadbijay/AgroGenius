package agrogenius.repository.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agrogenius.controller.entity.Admin;
import agrogenius.controller.entity.Crop;
import agrogenius.controller.entity.Recommendation;
import agrogenius.controller.entity.Region;
import agrogenius.controller.entity.User;
import agrogenius.controller.entity.WeatherData;
import agrogenius.repository.AdminRepository;
import agrogenius.repository.CropRepository;
import agrogenius.repository.RecommendationRepository;
import agrogenius.repository.RegionRepository;
import agrogenius.repository.UserRepository;
import agrogenius.repository.WeatherRepository;
import agrogenius.repository.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;
    
    @Autowired
    private CropRepository cropRepository;
    
    @Autowired
    private RecommendationRepository recommendationRepository;
    
    @Autowired
    private RegionRepository regionRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private WeatherRepository weatherDataRepository;

    // Admin Methods
    @Override
    public Admin saveAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    @Override
    public Optional<Admin> getAdminById(Long adminId) {
        return adminRepository.findById(adminId);
    }

    @Override
    public Admin getAdminByName(String adminName) {
        return adminRepository.findByAdminName(adminName);
    }

    @Override
    public List<Admin> getAllAdmin() {
        return adminRepository.findAll();
    }

    @Override
    public Admin updateAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    @Override
    public void deleteAdmin(Long adminId) {
        adminRepository.deleteById(adminId);
    }

    // Crop Methods
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
    public List<Crop> getAllCrop() {
        return cropRepository.findAll();
    }

    @Override
    public void deleteCrop(Long cropId) {
        cropRepository.deleteById(cropId);
    }

    // Recommendation Methods
    @Override
    public Recommendation saveRecommendation(Recommendation recommendation) {
        return recommendationRepository.save(recommendation);
    }

    @Override
    public Optional<Recommendation> getRecommendationById(Long id) {
        return recommendationRepository.findById(id);
    }

    @Override
    public List<Recommendation> getAllRecommendations() {
        return recommendationRepository.findAll();
    }

    @Override
    public void deleteRecommendation(Long id) {
        recommendationRepository.deleteById(id);
    }

    // Region Methods
    @Override
    public Region saveRegion(Region region) {
        return regionRepository.save(region);
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

    // User Methods
    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User getUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    // WeatherData Methods
    @Override
    public WeatherData saveWeatherData(WeatherData weatherData) {
        return weatherDataRepository.save(weatherData);
    }

    @Override
    public Optional<WeatherData> getWeatherDataById(Long id) {
        return weatherDataRepository.findById(id);
    }

    @Override
    public WeatherData getWeatherDataBySoilType(String soilType) {
        List<WeatherData> weatherDataList = weatherDataRepository.findBySoilType(soilType);
        if (!weatherDataList.isEmpty()) {
            return weatherDataList.get(0); 
        }
        return null; 
    }



    @Override
    public List<WeatherData> getAllWeatherData() {
        return weatherDataRepository.findAll();
    }

    @Override
    public void deleteWeatherData(Long id) {
        weatherDataRepository.deleteById(id);
    }
}
