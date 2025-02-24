package agrogenius.repository.service;

import agrogenius.controller.entity.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
	User saveUser(User user);
    Optional<User> getUserById(Long id);  
    User getUserByUserName(String userName);  
    User getUserByEmail(String email);  
    List<User> getAllUsers();  
    void deleteUser(Long id);
	User updateUser(User user);
}
