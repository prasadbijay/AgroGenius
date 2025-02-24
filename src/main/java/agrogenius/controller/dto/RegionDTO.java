package agrogenius.controller.dto;

import java.util.List;

import agrogenius.controller.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegionDTO {
    private Long id;
    
    private String regionName;
    private String state;
    private List<User> users;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public RegionDTO() {
		
	}
	public RegionDTO(Long id, String regionName, String state, List<User> users) {
		super();
		this.id = id;
		this.regionName = regionName;
		this.state = state;
		this.users = users;
	}
    

}
