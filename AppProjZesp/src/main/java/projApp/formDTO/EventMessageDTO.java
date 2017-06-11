package projApp.formDTO;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EventMessageDTO {

    @NotNull
    @Size(min=3, max=120)
    private String message;
    
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
