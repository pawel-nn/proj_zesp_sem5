package projApp.model.eventMessage;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import projApp.model.client.Client;
import projApp.model.employee.Employee;
import projApp.model.event.Event;

/**
 * @authors Pawe³ Miszkiel & Rafa³ Teodorowski
 * 
 * 		PW, Wydzia³ Elektryczny - Informatyka - semestr VI
 *
 * 2017-06-13
 */

@Entity
@Table(name = "event_messages")
public class EventMessage {

	private Integer eventMessageId;
	private String message;
	private Employee employee;
	private Client client;
	private Event event;
	
	public EventMessage() {};
	
	public EventMessage(String message, Employee employee, Client client, Event event) {
		this.message = message;
		this.employee = employee;
		this.client = client;
		this.event = event;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "event_message_id", unique = true, nullable = false)
	public Integer getEventMessageId() {
		return this.eventMessageId;
	}
	
	public void setEventMessageId(Integer eventMessageId) {
		this.eventMessageId = eventMessageId;
	}

	@Column(name = "message", nullable = false, length = 60)
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinColumn(name = "employee_id", nullable = true)
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinColumn(name = "client_id", nullable = true)
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "event_id", nullable = false)
	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}
	
}
