package funix.huutt.prj.validator;

public interface Validation {
	
	void resetValidation();
	
	boolean isValidPassword(String password);
	
	boolean isValidConfirmPassword(String password, String confirmPassword);
	
	boolean isValidUsername(String userName);
	
	boolean isValidEmail(String email);
	
	boolean isValidPhoneNumber(String phoneNumber);
	

}
