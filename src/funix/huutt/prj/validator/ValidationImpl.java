package funix.huutt.prj.validator;

import java.util.regex.Pattern;

public class ValidationImpl implements Validation {
	
	private String errorPassword;
	private String errorConfirmPassword;
	private String errorUsername;
	private String errorEmail;
	
	public ValidationImpl() {
		errorPassword = 
		errorConfirmPassword =
		errorEmail = 
		errorUsername = "";
	}

	@Override
	public boolean isValidPassword(String password) {
		
		if(password.length() < 8) {
			errorPassword += "Mật khẩu phải có ít nhất 8 ký tự.\n";
			return false;
		}
		
		if(password.contains(password)) {
			
			Pattern pattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).+$");
			if(!pattern.matcher(password).matches()) {
				errorPassword += "Mật khẩu phải bao gồm chữ viết HOA, chữ viết thường và số";
				return false;
			}
		}
		
		return true;
	}

	@Override
	public boolean isValidConfirmPassword(String password, String confirmPassword) {

		if(!password.equals(confirmPassword)) {
			
			errorConfirmPassword += "Nhắc lại mật khẩu phải giống mật khẩu.";
			return false;
		}
		return true;
		
	}

	@Override
	public boolean isValidUsername(String userName) {

		
		return false;
	}

	@Override
	public boolean isValidEmail(String email) {

		if(!email.contains("@")) {
			errorEmail += "Email bạn nhập không hợp lệ.";
			return false;
		}
		return true;
	}

	@Override
	public boolean isValidPhoneNumber(String phoneNumber) {
			
		try {
			
			Long.parseLong(phoneNumber);
			return true;
			
		} catch (Exception e) {
			phoneNumber += "Số điện thoại bạn nhập không hợp lệ.";
			return false;
		}
		
	}

	public String getErrorPassword() {
		return errorPassword;
	}

	public String getErrorConfirmPassword() {
		return errorConfirmPassword;
	}

	public void setErrorUsername(String errorUsername) {
		this.errorUsername += errorUsername;
	}

	public String getErrorUsername() {
		return errorUsername;
	}

	public String getErrorEmail() {
		return errorEmail;
	}

	public void setErrorPassword(String errorPassword) {
		this.errorPassword = errorPassword;
	}

	public void setErrorConfirmPassword(String errorConfirmPassword) {
		this.errorConfirmPassword = errorConfirmPassword;
	}

	public void setErrorEmail(String errorEmail) {
		this.errorEmail = errorEmail;
	}

	@Override
	public void resetValidation() {
		errorPassword = 
		errorConfirmPassword =
		errorEmail = 
		errorUsername = "";
		
	}

}
