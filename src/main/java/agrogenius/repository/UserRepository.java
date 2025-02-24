package agrogenius.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import agrogenius.controller.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email); // This method is already in your service
    User findByUserName(String userName); // Add this method if you want to query by username
}
