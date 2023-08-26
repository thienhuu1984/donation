package funix.huutt.prj.view;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class AccountView {
	
	private int id;
	
	
	private String username; // have to unique

	private String password; 
	
	private String confirmPassword;  // the same password field
	
	private String oldPassword;
	
	private int roleId;
	
	public AccountView() {

	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	@Override
	public String toString() {
		return "AccountView [username=" + username + ", password=" + password + ", confirmPassword=" + confirmPassword
				+ "]";
	}
	

}
