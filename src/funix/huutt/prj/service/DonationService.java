package funix.huutt.prj.service;

import java.util.List;


import funix.huutt.prj.entity.Donation;
import funix.huutt.prj.entity.Role;
import funix.huutt.prj.entity.User;
import funix.huutt.prj.entity.UserDonation;

public interface DonationService {

	List<Donation> getDonations();

	Donation getDonation(int id);
	
	User login(String username, String password);

	User getUser(int userId);

	void saveUserDonation(UserDonation ud);

	List<UserDonation> getUserDonations();

	void saveDonation(Donation dona);
	
	List<UserDonation> getUserDonations(int donationId);

	boolean isValidUsername(String username);

	Role getRole(int i);

	void saveUser(User user);

	List<User> getUsers();

	Role getRole(String string);

	User getUser(String username);



}
