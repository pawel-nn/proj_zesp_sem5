package projApp.model.cooperation;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.OrderBy;

import projApp.model.client.Client;
import projApp.model.employee.Employee;
import projApp.model.event.Event;


@Entity
@Table(name = "cooperation")
public class Cooperation {

	private Integer cooperationId;
	private Date dateOfLastEvent;
	private String typeOfCooperation;
	private Client client;
	private Employee employee;
	private List<Event> events = new ArrayList<Event>();
	
	public Cooperation() {}
	
	public Cooperation(Date dateOfLastEvent, Client client, Employee employee, List<Event> events) {
		this.dateOfLastEvent = dateOfLastEvent;
		this.client = client;
		this.employee = employee;
		this.events = events;
	}
	
	public Cooperation(Integer cooperationId, Date dateOfLastEvent, Client client, Employee employee, List<Event> events) {
		this.dateOfLastEvent = dateOfLastEvent;
		this.client = client;
		this.employee = employee;
		this.events = events;
		this.cooperationId = cooperationId;
	}
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "cooperation_id", unique = true, nullable = false)
	public Integer getCooperationId() {
		return cooperationId;
	}

	public void setCooperationId(Integer cooperationId) {
		this.cooperationId = cooperationId;
	}

	@Column(name = "date_of_last_event", nullable = true)
	@OrderBy("date_of_last_event")
	public Date getDateOfLastEvent() {
		return dateOfLastEvent;
	}

	public void setDateOfLastEvent(Date dateOfLastEvent) {
		this.dateOfLastEvent = dateOfLastEvent;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "client_id", nullable = false)
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employee_id", nullable = false)
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "cooperation")
	public List<Event> getEvents() {
		return events;
	}
	
	public void setEvents(List<Event> events) {
		this.events = events;
	}

	@Column(name = "type_of_cooperation", nullable = false, length = 45)
	public String getTypeOfCooperation() {
		return typeOfCooperation;
	}

	public void setTypeOfCooperation(String typeOfCooperation) {
		this.typeOfCooperation = typeOfCooperation;
	}

}
