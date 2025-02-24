package agrogenius.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import agrogenius.controller.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin,Long>{

	Admin findByAdminName(String adminName);
	
}
