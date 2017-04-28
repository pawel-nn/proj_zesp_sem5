package projApp.model.employee;


import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import projApp.model.user.User;

@Entity
@Table(name = "employees")
public class Employee {

	private Integer employeeId;
	private String email;
	private String firstName;
	private String lastName;
	private String position;
	private String salary;
	private String city;
	private String cityPostCode;
	private String street;
	private String accommodationNumber;
	private String mobile;
	
	private User user;
	
	public Employee() {}
	
	public Employee( String firstName, String lastName, String email, String position, String salary, String city, String cityPostCode, String street, String accommodationNumber, String mobile ) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.position = position;
		this.salary = salary;
		this.city = city;
		this.cityPostCode = cityPostCode;
		this.street = street;
		this.accommodationNumber = accommodationNumber;
		this.mobile = mobile;
	}

	public Employee( String firstName, String lastName, String email, String position, String salary, User user, String city, String cityPostCode, String street, String accommodationNumber, String mobile ) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.position = position;
		this.salary = salary;
		this.user = user;	
		this.city = city;
		this.cityPostCode = cityPostCode;
		this.street = street;
		this.accommodationNumber = accommodationNumber;
		this.mobile = mobile;
	}	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "employee_id", unique = true, nullable = false)
	public Integer getEmployeeId() {
		return this.employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	
	@Column(name = "email", unique = true, nullable = false, length = 45)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "first_name", nullable = false, length = 45)
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "last_name", nullable = false, length = 45)
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinColumn(name = "username", nullable = false)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	@Column(name = "position", nullable = false, length = 45)
	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	@Column(name = "salary", nullable = false, length = 12)
	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}
	
	@Column(name = "city", nullable = false, length = 45)
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	@Column(name = "street", nullable = false, length = 45)
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}
	
	@Column(name = "city_post_code", nullable = false, length = 45)
	public String getCityPostCode() {
		return cityPostCode;
	}

	public void setCityPostCode(String cityPostCode) {
		this.cityPostCode = cityPostCode;
	}
	
	@Column(name = "accommodation_number", nullable = false, length = 45)
	public String getAccommodationNumber() {
		return accommodationNumber;
	}

	public void setAccommodationNumber(String accommodationNumber) {
		this.accommodationNumber = accommodationNumber;
	}
	
	@Column(name = "mobile", nullable = false, length = 45)
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	@Override
	public String toString() {
		return "Employee Id=" + employeeId + ", Name=" + firstName + "." + lastName + ", email=" + email;
	}
	
}

