package agrogenius.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import agrogenius.controller.entity.Admin;
import agrogenius.exception.AdminNotFoundException;
import agrogenius.repository.service.AdminService;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/api/admins")  // ✅ Updated URL structure
public class AdminController {

    @Autowired
    @Lazy
    private AdminService adminService;

  
    @GetMapping
    public ResponseEntity<List<Admin>> getAllAdmins() {
        List<Admin> admins = adminService.getAllAdmin();
        return ResponseEntity.ok(admins);
    }

    //  Fetch admin by ID at /api/admins/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Admin> getAdminById(@PathVariable Long id) {
        Admin admin = adminService.getAdminById(id)
                .orElseThrow(() -> new AdminNotFoundException("Admin not found with id " + id));
        return ResponseEntity.ok(admin);
    }

    // ✅ Create a new admin at /api/admins
    @PostMapping
    public ResponseEntity<Admin> createAdmin(@RequestBody Admin admin) {
        Admin savedAdmin = adminService.saveAdmin(admin);
        return ResponseEntity.ok(savedAdmin);
    }

    // ✅ Update admin at /api/admins/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Admin> updateAdmin(@PathVariable Long id, @RequestBody Admin admin) {
        // Throw exception if admin is not found
        adminService.getAdminById(id)
                .orElseThrow(() -> new AdminNotFoundException("Admin not found with id " + id));

        admin.setId(id); // Ensure ID is set before updating
        Admin updatedAdmin = adminService.updateAdmin(admin);
        return ResponseEntity.ok(updatedAdmin);
    }

    // ✅ Delete admin at /api/admins/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable Long id) {
        // Throw exception if admin is not found
        adminService.getAdminById(id)
                .orElseThrow(() -> new AdminNotFoundException("Admin not found with id " + id));

        adminService.deleteAdmin(id);
        return ResponseEntity.noContent().build();
    }
}
