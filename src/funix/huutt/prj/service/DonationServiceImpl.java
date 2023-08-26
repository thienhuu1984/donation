package funix.huutt.prj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import funix.huutt.prj.dao.DonationDAO;
import funix.huutt.prj.dao.RoleDAO;
import funix.huutt.prj.dao.UserDAO;
import funix.huutt.prj.dao.UserDonationDAO;
import funix.huutt.prj.entity.Donation;
import funix.huutt.prj.entity.Role;
import funix.huutt.prj.entity.User;
import funix.huutt.prj.entity.UserDonation;

@Service
public class DonationServiceImpl implements DonationService {
	
	@Autowired
	private DonationDAO donationDao;
	
	@Autowired
	private UserDonationDAO userDonationDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	
	@Autowired
	private RoleDAO roleDao;

	@Override
	@Transactional
	public List<Donation> getDonations() {
		return donationDao.getDonations();
	}

	@Override
	@Transactional
	public Donation getDonation(int id) {
		return donationDao.getDonation(id);
	}

	@Override
	@Transactional
	public User login(String username, String password) {

		return userDAO.login(username, password);
	}

	@Override
	@Transactional
	public User getUser(int userId) {
		return userDAO.getUser(userId);
	}

	@Override
	@Transactional
	public void saveUserDonation(UserDonation ud) {
		
		userDonationDAO.save(ud);
		
	}

	@Override
	@Transactional
	public List<UserDonation> getUserDonations() {

		return userDonationDAO.getUserDonations();
	}

	@Override
	@Transactional
	public void saveDonation(Donation dona) {
		donationDao.saveDonation(dona);
		
	}
	
	@Override
	@Transactional
	public List<UserDonation> getUserDonations(int donationId) {
		return userDonationDAO.getUserDonations(donationId);
	}

	@Override
	@Transactional
	public boolean isValidUsername(String username) {
		return userDAO.getUser(username) == null;
	}

	@Override
	@Transactional
	public Role getRole(int i) {
		return roleDao.getRole(i);
	}

	@Override
	@Transactional
	public void saveUser(User user) {

		userDAO.saveUser(user);
		
	}

	@Override
	@Transactional
	public List<User> getUsers() {
		return userDAO.getUsers();
	}

	@Override
	@Transactional
	public Role getRole(String string) {
		return roleDao.getRole(string);
	}

	@Override
	@Transactional
	public User getUser(String username) {
		// TODO Auto-generated method stub
		return null;
	}


	
}
