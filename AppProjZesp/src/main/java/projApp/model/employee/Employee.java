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
	
	private User user;
	
	public Employee() {}
	
	public Employee( String firstName, String lastName, String email, String position, String salary ) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.position = position;
		this.salary = salary;
	}

	public Employee( String firstName, String lastName, String email, String position, String salary, User user ) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.position = position;
		this.salary = salary;
		this.user = user;		
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
	
	@Override
	public String toString() {
		return "Employee Id=" + employeeId + ", Name=" + firstName + "." + lastName + ", email=" + email;
	}
	
}

