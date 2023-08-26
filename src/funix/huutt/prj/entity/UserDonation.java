package funix.huutt.prj.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="user_donation")
public class UserDonation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="message")
	private String message;
	
	@Column(name="money")
	private long money;
	
	@Column(name="hidden_user")
	private int hiddenUser;
	
	@Column(name="created")
	private Date created;
	
	@Column(name="status")
	private int status;
	
	@ManyToOne(cascade={CascadeType.DETACH,
						CascadeType.MERGE,
						CascadeType.PERSIST,
						CascadeType.REFRESH })
	@JoinColumn(name="user_id")
	private User user;
	
	@ManyToOne(cascade={CascadeType.DETACH,
			CascadeType.MERGE,
			CascadeType.PERSIST,
			CascadeType.REFRESH })
	@JoinColumn(name="donation_id")
	private Donation donation;
	
	public UserDonation() {
	
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
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


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Donation getDonation() {
		return donation;
	}


	public void setDonation(Donation donation) {
		this.donation = donation;
	}


	public int getHiddenUser() {
		return hiddenUser;
	}


	public void setHiddenUser(int hiddenUser) {
		this.hiddenUser = hiddenUser;
	}


	@Override
	public String toString() {
		return "UserDonation [id=" + id + ", message=" + message + ", money=" + money + ", created=" + created
				+ ", status=" + status + ", user=" + user + ", donation=" + donation + "]";
	}




	
	
	
}
