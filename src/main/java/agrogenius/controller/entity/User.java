package agrogenius.controller.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "User can't be blank: ")
    @Size(min = 5, max = 50, message = "Please enter user full name: ")
    @Column(nullable = false)
    private String userName;
    
    @NotBlank(message = "Email can't be blank: ")
    @Size(min = 15, max = 100, message = "Please enter valid email: ")
    @Email(regexp = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$", 
           message = "Enter a valid and working email")
    @Column(nullable = false, unique = true)
    private String email;
    
    @NotBlank(message = "Password can't be blank: ")
    @Size(min = 10, max = 50, message = "Please enter a strong password (e.g., use a special character, use uppercase alphabet): ")
    @Column(nullable = false)
    private String password;
    
    @NotBlank(message = "Phone can't be blank: ")
    @Pattern(regexp = "^[6789]{1}[0-9]{9}$", message = "Phone number should start with 6,7,8,9 and be a valid number: ")
    @Column(nullable = false, unique = true)
    private String phone;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "region_id")
    @JsonManagedReference
    private Region region;
    
    @Version
    @Column(nullable = true)
    private Long version;



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

    
}
