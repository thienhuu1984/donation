package funix.huutt.prj.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="donation")
public class Donation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="code")
	private String code;
	
	@Column(name="name")
	private String name;
	
	@Column(name="organization_name")
	private String organizationName;
	
	@Column(name="description")
	private String description;
	
	@Column(name="start_date")
	private Date startDate;
	
	@Column(name="end_date")
	private Date endDate;
	
	@Column(name="phone_number")
	private String phoneNumber;
	
	@Column(name="money")
	private long money;
	
	@Column(name="created")
	private Date created;
	
	@Column(name="status")
	private int status;
	

	@OneToMany(mappedBy = "donation", 
				cascade = {CascadeType.DETACH, 
							CascadeType.MERGE,
							CascadeType.PERSIST,
							CascadeType.REFRESH})
	private List<UserDonation> userDonations;
	
	public Donation() {
		status = 0; // created
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		
		this.endDate = endDate;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public long getMoney() {
		return money;
	}

	public void setMoney(long money) {
		this.money = money;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
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

		if(this.userDonations == null) { 
			this.userDonations = new ArrayList<>();
		}
		
		this.userDonations.add(userDonation);
		
		userDonation.setDonation(this);
	}

	@Override
	public String toString() {
		return "Donation [id=" + id + ", code=" + code + ", name=" + name + ", organizationName=" + organizationName
				+ ", description=" + description + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", phoneNumber=" + phoneNumber + ", money=" + money + ", created=" + created + ", status=" + status
				+ "]";
	}
	
	public void donate(long money) { 
		this.money += money;
	}

	public void updateStatus() {
		
		Date now = new Date();
		
		if( now.after(endDate) ) 
			this.status = -1 ; // finished
		else if(now.equals(startDate) ||  now.after(startDate))
			this.status = 1; // donating
		else this.status = 0; // created
		
		
	}

	public void finish() {
		
		endDate = new Date();
		status = -1;
		
	}
	
	public void start() {
		startDate = new Date();
		status = 1;
	}
	
	
	
	

}
