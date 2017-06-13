package projApp.formDTO;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import projApp.model.event.Event;

/**
 * @authors Pawe³ Miszkiel & Rafa³ Teodorowski
 * 
 * 		PW, Wydzia³ Elektryczny - Informatyka - semestr VI
 *
 * 2017-06-13
 */

public class EventDocumentDTO {
    
	private Integer eventDocumentId;
	private String name;
	private String type;
	private String path;
	
	@NotNull
	private MultipartFile eventDocument;
	
	@NotNull
    @Size(min=5, max=120)
	private String description;
	
	private Event event;
	
	public Integer getEventDocumentId() {
		return eventDocumentId;
	}
	public void setEventDocumentId(Integer eventDocumentId) {
		this.eventDocumentId = eventDocumentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Event getEvent() {
		return event;
	}
	public void setEvent(Event event) {
		this.event = event;
	}
	public MultipartFile getEventDocument() {
		return eventDocument;
	}
	public void setEventDocument(MultipartFile eventDocument) {
		this.eventDocument = eventDocument;
	}

}