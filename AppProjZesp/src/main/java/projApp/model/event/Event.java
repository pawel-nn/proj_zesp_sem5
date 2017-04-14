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
import projApp.model.document.Document;


@Entity
@Table(name = "events")
public class Event {

	private Integer actionId;
	private String topic;
	private String content;
	private Date registrationDate;
	private Cooperation cooperation;
	private List <Document> documents = new ArrayList<Document>();
	
	public Event() {}
	
	public Event(String topic, String content, Date registrationDate, Cooperation cooperation, List <Document> documents) {
		this.topic = topic;
		this.content = content;
		this.registrationDate = registrationDate;
		this.cooperation = cooperation;
		this.documents = documents;
	}

	public Event(Integer actionId, String topic, String content, Date registrationDate, Cooperation cooperation, List <Document> documents) {
		this.actionId = actionId;
		this.topic = topic;
		this.content = content;
		this.registrationDate = registrationDate;
		this.cooperation = cooperation;
		this.documents = documents;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ecent_id", unique = true, nullable = false)
	public Integer getActionId() {
		return this.actionId;
	}
	
	public void setActionId(Integer actionId) {
		this.actionId = actionId;
	}

	@Column(name = "topic", nullable = false, length = 45)
	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
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
	@JoinColumn(name = "cooperationId", nullable = false)
	public Cooperation getCooperation() {
		return this.cooperation;
	}

	public void setCooperation(Cooperation cooperation) {
		this.cooperation = cooperation;
	}
	
	@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "event")
	public List<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}

}

