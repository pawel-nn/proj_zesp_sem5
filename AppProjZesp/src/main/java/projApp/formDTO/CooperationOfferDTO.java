package projApp.formDTO;

import javax.validation.constraints.NotNull;

public class CooperationOfferDTO {

	private Integer cooperationOfferId;
    @NotNull
	private String typeOfCooperation;
    @NotNull
	private String description;
    @NotNull
    private Integer clientId;
    @NotNull
    private Integer employeeId;
    
	public Integer getCooperationOfferId() {
		return cooperationOfferId;
	}
	public void setCooperationOfferId(Integer cooperationOfferId) {
		this.cooperationOfferId = cooperationOfferId;
	}
	public String getTypeOfCooperation() {
		return typeOfCooperation;
	}
	public void setTypeOfCooperation(String typeOfCooperation) {
		this.typeOfCooperation = typeOfCooperation;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
}
