package funix.huutt.prj.dao;

import java.util.List;

import funix.huutt.prj.entity.Donation;

public interface DonationDAO {

	List<Donation> getDonations();

	void updateStatus();

	void saveDonation(Donation dona);

	Donation getDonation(int id);

	void deleteDonation(int id);

	List<Donation> getDonations(String searchKey);

}
