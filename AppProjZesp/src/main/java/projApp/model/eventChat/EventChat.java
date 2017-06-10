package projApp.model.eventChat;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "event_chats")
public class EventChat {

	private Integer eventChatId;
	
	public EventChat() {};
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "event_id", unique = true, nullable = false)
	public Integer getEventChatId() {
		return this.eventChatId;
	}
	
	public void setEventChatId(Integer eventChatId) {
		this.eventChatId = eventChatId;
	}
	
}
