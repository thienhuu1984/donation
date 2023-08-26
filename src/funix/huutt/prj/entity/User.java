 package funix.huutt.prj.entity;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import java.util.List ;

@Entity
@Table(name="user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;
	
	@Column(name="full_name")
	private String fullName;

	@Column(name="address")
	private String address;
	
	@Column(name="phone_number")
	private String phoneNumber;
	
	@Column(name="email")
	private String email;
	
	@Column(name="note")
	private String note;
	
	@Column(name="created")
	private Date createdDate;
	
	@Column(name="status")
	private int status;
	
	@OneToMany(mappedBy = "user", 
			cascade = {CascadeType.DETACH, 
						CascadeType.MERGE,
						CascadeType.PERSIST,
						CascadeType.REFRESH})
	private List<UserDonation> userDonations;
	
	@ManyToOne(cascade={CascadeType.DETACH,
			CascadeType.MERGE,
			CascadeType.PERSIST,
			CascadeType.REFRESH })
	@JoinColumn(name="role_id")
	private Role role;
	
	
	public User() {
		// TODO Auto-generated constructor stub
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

	public List<UserDonation> getUserDonations() {
		return userDonations;
	}

	public void setUserDonations(List<UserDonation> userDonations) {
		this.userDonations = userDonations;
	}
	
	public void add(UserDonation userDonation) { 
		
		if(this.userDonations == null ) 
			this.userDonations = new ArrayList<>();
		
		this.userDonations.add(userDonation);
		
		userDonation.setUser(this);
	}
	

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	public boolean isBlock() {
		return this.status == -1;
	}
	
	public void block() {
		this.status = -1;
	}
	
	public void unblock() {
		this.status = 0;
	}

	

	@Override
	public String toString() {
		return "User [id=" + id +  ", fullName=" + fullName
				+ ", address=" + address + ", phoneNumber=" + phoneNumber + ", email=" + email + ", note=" + note
				+ ", createdDate=" + createdDate + ", status=" + status + "]";
	}
	
	
	
	
	
	
	
}
