package funix.huutt.prj.entity;

public class Validation {

	
	private String errorPassword;
	private String errorOldPassword;
	private String errorConfirmPassword;
	private String errorUsername;
	private String errorEmail;
	private String errorLogin;
	private String errorFullName;
	private String requestLogin;
	
	public Validation() {
	}
	

	public void resetValidation() {
		errorPassword = 
		errorConfirmPassword =
		errorEmail = 
		errorUsername = 
		errorLogin = 
		errorFullName = 
		errorOldPassword = 
		requestLogin = "";
		
	}


	public String getErrorOldPassword() {
		return errorOldPassword;
	}


	public void setErrorOldPassword(String errorOldPassword) {
		this.errorOldPassword = errorOldPassword;
	}


	public String getErrorFullName() {
		return errorFullName;
	}


	public void setErrorFullName(String errorFullName) {
		this.errorFullName = errorFullName;
	}


	public String getErrorPassword() {
		return errorPassword;
	}


	public void setErrorPassword(String errorPassword) {
		this.errorPassword += errorPassword;
	}


	public String getErrorConfirmPassword() {
		return errorConfirmPassword;
	}


	public void setErrorConfirmPassword(String errorConfirmPassword) {
		this.errorConfirmPassword += errorConfirmPassword;
	}


	public String getErrorUsername() {
		return errorUsername;
	}


	public void setErrorUsername(String errorUsername) {
		this.errorUsername += errorUsername;
	}


	public String getErrorEmail() {
		return errorEmail;
	}


	public void setErrorEmail(String errorEmail) {
		this.errorEmail += errorEmail;
	}


	public String getErrorLogin() {
		return errorLogin;
	}


	public void setErrorLogin(String errorLogin) {
		this.errorLogin = errorLogin;
	}


	public String getRequestLogin() {
		return requestLogin;
	}


	public void setRequestLogin(String requestLogin) {
		this.requestLogin = requestLogin;
	}
	

}
