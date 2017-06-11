package projApp.formDTO;

import java.util.Date;

import javax.validation.constraints.NotNull;

public class CooperationDTO {

	private Integer cooperationId;
	private Date dateOfLastEvent;
    @NotNull
	private String subject;
	@NotNull
	private String typeOfCooperation;
    @NotNull
    private Integer clientId;
    @NotNull
    private Integer employeeId;
    @NotNull
    private String description;
    
	public Integer getCooperationId() {
		return cooperationId;
	}
	public void setCooperationId(Integer cooperationId) {
		this.cooperationId = cooperationId;
	}
	public Date getDateOfLastEvent() {
		return dateOfLastEvent;
	}
	public void setDateOfLastEvent(Date dateOfLastEvent) {
		this.dateOfLastEvent = dateOfLastEvent;
	}
	public String getTypeOfCooperation() {
		return typeOfCooperation;
	}
	public void setTypeOfCooperation(String typeOfCooperation) {
		this.typeOfCooperation = typeOfCooperation;
	}
	public Integer getClientId() {
		return clientId;
	}
	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}
	public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
    public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
