package projApp.formDTO;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @authors Pawe³ Miszkiel & Rafa³ Teodorowski
 * 
 * 		PW, Wydzia³ Elektryczny - Informatyka - semestr VI
 *
 * 2017-06-13
 */

public class NewPasswordDTO {

    @NotNull
    @Size(min=4, max=45)
    private String newPassword;
    
    @NotNull
    @Size(min=4, max=45)
    private String confirmNewPassword;
    
    public boolean arePasswordsEquals() {
    	return newPassword.equals(confirmNewPassword);
    }

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmNewPassword() {
		return confirmNewPassword;
	}

	public void setConfirmNewPassword(String confirmNewPassword) {
		this.confirmNewPassword = confirmNewPassword;
	}
	
}
