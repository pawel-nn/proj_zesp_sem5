package projApp.model.cooperationOffer;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import projApp.model.client.Client;
import projApp.model.employee.Employee;

@Entity
@Table(name = "cooperation_offers")
public class CooperationOffer {
	
	private Integer cooperationOfferId;
	private String typeOfCooperation;
	private String description;
	private Date dateOfOfferCreation;
	private Client client;
	private Employee employee;
	
	public CooperationOffer() {}
	
	public CooperationOffer(String typeOfCooperation, String description, Date dateOfOfferCreation, Client client, Employee employee) {
		this.dateOfOfferCreation = dateOfOfferCreation;
		this.client = client;
		this.employee = employee;
		this.typeOfCooperation = typeOfCooperation;
		this.description = description;
	}
	
	public CooperationOffer(String typeOfCooperation, String description, Integer cooperationOfferId, Date dateOfOfferCreation, Client client, Employee employee) {
		this.dateOfOfferCreation = dateOfOfferCreation;
		this.client = client;
		this.employee = employee;
		this.cooperationOfferId = cooperationOfferId;
		this.typeOfCooperation = typeOfCooperation;
		this.description = description;
	}
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "cooperation_offer_id", unique = true, nullable = false)
	public Integer getCooperationOfferId() {
		return cooperationOfferId;
	}
	public void setCooperationOfferId(Integer cooperationOfferId) {
		this.cooperationOfferId = cooperationOfferId;
	}
	
	@Column(name = "type_of_cooperation", nullable = false, length = 45)
	public String getTypeOfCooperation() {
		return typeOfCooperation;
	}
	public void setTypeOfCooperation(String typeOfCooperation) {
		this.typeOfCooperation = typeOfCooperation;
	}
	
	@Column(name = "description", nullable = false, length = 45)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name = "date_of_offer_creation", nullable = false, length = 45)
	@OrderBy("date_of_offer_creation")
	public Date getDateOfOfferCreation() {
		return dateOfOfferCreation;
	}
	public void setDateOfOfferCreation(Date dateOfOfferCreation) {
		this.dateOfOfferCreation = dateOfOfferCreation;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "client_id", nullable = true)
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employee_id", nullable = true)
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}
