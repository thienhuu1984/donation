package funix.huutt.prj.dao;

import java.util.List;


import funix.huutt.prj.entity.UserDonation;

public interface UserDonationDAO {

	List<UserDonation> getUserDonations();

	void save(UserDonation ud);

	UserDonation getUserDonation(int id);

	List<UserDonation> getUserDonations(int donationId);
}
