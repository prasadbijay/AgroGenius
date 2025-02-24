package agrogenius.controller.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Admin")
@Getter
@Setter
public class Admin {
	@Id
	@GeneratedValue (strategy=GenerationType.AUTO)
	private Long id;

	private String adminRole;  // Example: SuperAdmin, Moderator, etc.
    @NotBlank(message="User can't be blank: ")
    @Size(min=5,max=50,message="please enter user full name: ")
    @Column(nullable=false)
    private String adminName;
    
    @NotBlank(message="email can't be blank: ")
    @Size(min=15,max=100,message="please enter valid email: ")
    @Email( regexp="^[a-zA-Z0-9_+&*-]+(?:\\\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\\\.)+[a-zA-Z]{2,7}$",message="email can't be blank,and enter valid and working email")
    @Column(nullable=false)
    private String email;
    
    @NotBlank(message="password can't be blank: ")
    @Size(min=10,max=50,message="please Enter Strong password e.g..(use Special Character, use upper case alphabet): ")
    @Column(nullable=false)
    private String password;
    
    @NotBlank(message="phone can't be blank: ")
    @Size(min=10,max=10,message="please Enter valid phone: ")
    @Pattern( regexp="^[6789]{1}[0-9]{9}$",message = "Phone number should be start with 6,7,8,9 and working phone number: ")
    @Column(nullable=false)
    private String phone;

	public String getAdminRole() {
		return adminRole;
	}

	public void setAdminRole(String adminRole) {
		this.adminRole = adminRole;
	}
    
    public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


}
