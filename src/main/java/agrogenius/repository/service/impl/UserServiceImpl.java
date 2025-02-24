package agrogenius.repository.service.impl;

import agrogenius.controller.entity.User;
import agrogenius.controller.entity.Region; 
import agrogenius.repository.UserRepository;
import agrogenius.repository.RegionRepository; 
import agrogenius.repository.service.UserService;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RegionRepository regionRepository; 

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RegionRepository regionRepository) {
        this.userRepository = userRepository;
        this.regionRepository = regionRepository;
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

    @Override
    //saving or creating new user and checking weather region or region is null;
    public User saveUser(User user) {
        if (user.getRegion() == null || user.getRegion().getId() == null) {
            throw new IllegalArgumentException("Region cannot be null or missing.");
        }

        Region region = regionRepository.findById(user.getRegion().getId())
                .orElseThrow(() -> new EntityNotFoundException("Region not found"));

        user.setRegion(region);

        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        if (!userRepository.existsById(user.getId())) {
            throw new EntityNotFoundException("User not found for update.");
        }
        return userRepository.saveAndFlush(user);
    }
}
