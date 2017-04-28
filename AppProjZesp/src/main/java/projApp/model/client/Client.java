package projApp.model.client;

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


import projApp.wwwApp.WebUser;


@Entity
@Table(name = "clients")
public class Client {

	private Integer clientId;
	private String email;
	private String firstName;
	private String lastName;
	private String city;
	private String cityPostCode;
	private String street;
	private String accommodationNumber;
	private String mobile;
	
	private WebUser webUser;
	
	public Client( String firstName, String lastName, String email, String city, String cityPostCode, String street, String accommodationNumber, String mobile) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.city = city;
		this.cityPostCode = cityPostCode;
		this.street = street;
		this.accommodationNumber = accommodationNumber;
		this.mobile = mobile;
	}
	
	public Client() {}
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "client_id", unique = true, nullable = false)
	public Integer getClientId() {
		return this.clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
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
		return "Client Id=" + clientId + ", Names=" + firstName + "." + lastName + ", email=" + email;
	}

	@OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinColumn(name = "username", nullable = false)
	public WebUser getWebUser() {
		return webUser;
	}

	public void setWebUser(WebUser webUser) {
		this.webUser = webUser;
	}
}
