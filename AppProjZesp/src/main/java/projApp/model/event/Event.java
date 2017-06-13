package projApp.model.event;

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
import javax.persistence.OrderBy;
import javax.persistence.Table;

import projApp.model.cooperation.Cooperation;
import projApp.model.eventDocument.EventDocument;
import projApp.model.eventMessage.EventMessage;

/**
 * @authors Pawe³ Miszkiel & Rafa³ Teodorowski
 * 
 * 		PW, Wydzia³ Elektryczny - Informatyka - semestr VI
 *
 * 2017-06-13
 */

@Entity
@Table(name = "events")
public class Event {

	private Integer eventId;
	private String subject;
	private String eventType;
	private String content;
	private Date registrationDate;
	private Cooperation cooperation;
	private List <EventDocument> eventDocuments = new ArrayList<EventDocument>();
	private List <EventMessage> eventMessages = new ArrayList<EventMessage>();
	
	public Event() {}
	
	public Event(String subject, String eventType, String content, Date registrationDate, Cooperation cooperation, List <EventDocument> eventDocuments) {
		this.subject = subject;
		this.content = content;
		this.registrationDate = registrationDate;
		this.cooperation = cooperation;
		this.eventDocuments = eventDocuments;
		this.eventType = eventType;
	}

	public Event(Integer eventId, String eventType, String subject, String content, Date registrationDate, Cooperation cooperation, List <EventDocument> eventDocuments) {
		this.eventId = eventId;
		this.subject = subject;
		this.content = content;
		this.registrationDate = registrationDate;
		this.cooperation = cooperation;
		this.eventDocuments = eventDocuments;
		this.eventType = eventType;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "event_id", unique = true, nullable = false)
	public Integer getEventId() {
		return this.eventId;
	}
	
	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

	@Column(name = "subject", nullable = false, length = 45)
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	@Column(name = "content", nullable = false, length = 45)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "registration_date", nullable = false)
	@OrderBy("registration_date")
	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cooperation_id", nullable = false)
	public Cooperation getCooperation() {
		return this.cooperation;
	}

	public void setCooperation(Cooperation cooperation) {
		this.cooperation = cooperation;
	}
	
	@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "event")
	public List<EventDocument> getEventDocuments() {
		return eventDocuments;
	}

	public void setEventDocuments(List<EventDocument> eventDocuments) {
		this.eventDocuments = eventDocuments;
	}

	@Column(name = "event_type", nullable = false)
	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "event")
	public List <EventMessage> getEventMessages() {
		return eventMessages;
	}

	public void setEventMessages(List <EventMessage> eventMessages) {
		this.eventMessages = eventMessages;
	}

}

