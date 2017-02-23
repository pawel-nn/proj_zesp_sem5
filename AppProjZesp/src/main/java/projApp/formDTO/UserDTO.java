package projApp.formDTO;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDTO {

    @NotNull
    @Size(min=3, max=45)
    private String username;

    @NotNull
    @Size(min=4, max=45)
    private String password;

    @NotNull
    @Size(min=4, max=45)
    private String confirmPassword;
    
    @NotNull
    @Size(min=1, max=45)
    private String firstName;

    @NotNull
    @Size(min=1, max=45)
    private String lastName;
    
    @NotNull
    @Size(min=6, max=45)
    private String email;
    
    @NotNull
    @Size(min=3, max=45)
    private String position;
    
    private String salary;
    
    public boolean arePasswordsEquals() {
    	return password.equals(confirmPassword);
    }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}
	
}
