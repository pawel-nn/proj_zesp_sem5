package projApp.model.document;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import projApp.model.event.Event;


@Entity
@Table(name = "documents")
public class Document {

	private Integer documentId;
	private String name;
	private String type;
	private String path;
	private String description;
	private Event event;
	
	public Document() {}
	
	public Document(String name, String type, String path, String description) {
		this.name = name;
		this.type = type;
		this.path = path;
		this.description = description;
	}
	
	public Document(Integer documentId, String name, String type, String path, String description) {
		this.documentId= documentId;
		this.name = name;
		this.type = type;
		this.path = path;
		this.description = description;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "document_id", unique = true, nullable = false)
	public Integer getDocumentId() {
		return this.documentId;
	}
	
	public void setDocumentId(Integer documentId) {
		this.documentId = documentId;
	}
	
	@Column(name = "name", nullable = false, length = 45)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "type", nullable = false, length = 45)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "path", nullable = false, length = 45)
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Column(name = "description", nullable = false, length = 90)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinColumn (name="event_id", nullable=false)
	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}
	
}

