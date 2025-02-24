package agrogenius.repository.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetialService {
	UserDetails loadUserByUsername(String userName);
}
