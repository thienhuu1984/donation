package funix.huutt.prj.view;

import java.util.Date;

public class UserView {
	
	private int id;
	private String fullName;
	private String address;
	private String phoneNumber;
	private String email;
	private String note;
	private Date createdDate;
	private int status;
	private int roleId;
	private String roleName;
	private String process;

	public UserView() {
		this.process = "processNewUser";
	}

	public UserView(String process) {
		this.process = process;
	}

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Override
	public String toString() {
		return "User [id=" + id +  ", fullName=" + fullName
				+ ", address=" + address + ", phoneNumber=" + phoneNumber + ", email=" + email + ", note=" + note
				+ ", createdDate=" + createdDate + ", status=" + status + ", roleId=" + roleId + ", roleName="
				+ roleName + "]";
	}



}
