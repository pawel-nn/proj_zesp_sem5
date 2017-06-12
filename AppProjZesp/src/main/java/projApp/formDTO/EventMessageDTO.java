package projApp.formDTO;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EventMessageDTO {

	@NotNull
    @Size(min=5, max=120)
    private String chatMessage;

	@NotNull
    private Integer employeeId;
    
    private Integer clientId;
    
	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

    public String getChatMessage() {
		return chatMessage;
	}

	public void setChatMessage(String chatMessage) {
		this.chatMessage = chatMessage;
	}
	
}
