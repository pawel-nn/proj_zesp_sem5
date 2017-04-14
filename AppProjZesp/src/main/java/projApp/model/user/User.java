package projApp.model.user;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import projApp.model.user.UserRole;


@Entity
@Table(name = "spring_users") 
public class User {

	private String username;
	private String password;
	private boolean enabled;
	private Set<UserRole> userRole = new HashSet<UserRole>(0);

	public User() {
	}

	public User(String username, String password, boolean enabled) {
		this.username = username;
		this.password = password;
		this.enabled = enabled;
	}

	public User(String username, String password,
		boolean enabled, Set<UserRole> userRole) {
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.userRole = userRole;
	}

	@Id
	@Column(name = "username", unique = true, nullable = false, length = 45)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "password", nullable = false, length = 60)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "enabled", nullable = false)
	public boolean isEnabled() {
		return this.enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "user")
	public Set<UserRole> getUserRole() {
		return this.userRole;
	}

	public void setUserRole(Set<UserRole> userRole) {
		this.userRole = userRole;
	}
	
	public boolean hasAdminRole() {
    	Iterator<UserRole> it = userRole.iterator();
    	while(it.hasNext()) {
    		UserRole ur = (UserRole) it.next();
    		if(ur.getRole().equals("ROLE_ADMIN"))
    			return true;
    	}
		return false;
	}
	
	public String userRoleToString() {
		String roles = null;
		ArrayList<String> al = new ArrayList<String>();
    	Iterator<UserRole> it = userRole.iterator();
    	if(it.hasNext()) {
    		UserRole ur = (UserRole) it.next();
    		roles = ur.getRole();
    	}
    	if(roles == null) roles = "Roles: empty!";
		return roles;
	}

}