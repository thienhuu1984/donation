package funix.huutt.prj.view;

public class DonationDetailView {
	
	private int id;
	private String message;
	private long money;
	private int donationId;
	private String donationName;
	private int userId;
	private String userName;
	private boolean hiddenName;
	private int status;
	private String created;
	

	public DonationDetailView() {

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

	public int getDonationId() {
		return donationId;
	}

	public void setDonationId(int donationId) {
		this.donationId = donationId;
	}

	public String getDonationName() {
		return donationName;
	}

	public void setDonationName(String donationName) {
		this.donationName = donationName;
	}
	
	public boolean getHiddenName() {
		return hiddenName;
	}

	public boolean isHiddenName() {
		return hiddenName;
	}

	public void setHiddenName(boolean hiddenName) {
		this.hiddenName = hiddenName;
	}
	

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	@Override
	public String toString() {
		return "DonationDetailView [id=" + id +  ", money=" + money + ", donationId=" + donationId
				+ ", userId=" + userId + ", userName=" + userName + ", status=" + status + ", created=" + created + "]";
	}

}
