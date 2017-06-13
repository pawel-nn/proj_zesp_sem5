package projApp.formDTO;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import projApp.model.employee.Employee;

/**
 * @authors Pawe³ Miszkiel & Rafa³ Teodorowski
 * 
 * 		PW, Wydzia³ Elektryczny - Informatyka - semestr VI
 *
 * 2017-06-13
 */

public class EmployeeUpdateDTO {

    @Size(min=3, max=45)
    private String username;

    @Size(min=4, max=45)
    private String password;

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
    
    @Size(min=3, max=45)
    private String position;
    
    private String salary;
    
    @NotNull
    @Size(min=1, max=45)
    private String city;
    
    @NotNull
    @Size(min=1, max=45)
    private String cityPostCode;
    
    @NotNull
    @Size(min=1, max=45)
    private String street;
    
    @NotNull
    @Size(min=1, max=45)
    private String accommodationNumber;
    
    @NotNull
    @Size(min=1, max=45)
    private String mobile;
    
    @NotNull
    @Size(min=3, max=120)
    private String profileDescription;
    
    private Integer employeeId;
    
	private String pathToProfilePhoto;
	
    public void setUp(Employee employee) {
		this.username = employee.getUser().getUsername();
		this.password = employee.getUser().getPassword();
		this.confirmPassword = employee.getUser().getPassword();
		this.firstName = employee.getFirstName();
		this.lastName = employee.getLastName();
		this.email = employee.getEmail();
		this.position = employee.getPosition();
		this.salary = employee.getSalary();
		this.city = employee.getCity();
		this.cityPostCode = employee.getCityPostCode();
		this.street = employee.getStreet();
		this.accommodationNumber = employee.getAccommodationNumber();
		this.mobile = employee.getMobile();
		this.profileDescription = employee.getProfileDescription();
		this.employeeId = employee.getEmployeeId();
		this.pathToProfilePhoto = employee.getPathToProfilePhoto();
	}

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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCityPostCode() {
		return cityPostCode;
	}

	public void setCityPostCode(String cityPostCode) {
		this.cityPostCode = cityPostCode;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getAccommodationNumber() {
		return accommodationNumber;
	}

	public void setAccommodationNumber(String accommodationNumber) {
		this.accommodationNumber = accommodationNumber;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getProfileDescription() {
		return profileDescription;
	}

	public void setProfileDescription(String profileDescription) {
		this.profileDescription = profileDescription;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getPathToProfilePhoto() {
		return pathToProfilePhoto;
	}

	public void setPathToProfilePhoto(String pathToProfilePhoto) {
		this.pathToProfilePhoto = pathToProfilePhoto;
	}
	
	
}
