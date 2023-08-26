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
public class AdminServiceImpl implements AdminService {

	@Autowired
	private RoleDAO roleDao;

	
	@Autowired
	private UserDAO userDao;
	
	@Autowired
	private DonationDAO donationDAO;
	
	@Autowired
	private UserDonationDAO userDonationDAO;
	
	@Override
	@Transactional
	public List<Role> getRoles() {
		return roleDao.getRoles();
	}

	@Override
	@Transactional
	public void saveRole(Role role) {
		roleDao.saveRole(role);
		
	}

	@Override
	@Transactional
	public List<User> getUsers() {
		return userDao.getUsers();
	}

	@Override
	@Transactional
	public void saveUser(User user) {

		userDao.saveUser(user);
		
	}

	@Override
	@Transactional
	public Role getRole(int roleId) {
		return roleDao.getRole(roleId);
	}

	@Override
	@Transactional
	public User getUser(int id) {
		return userDao.getUser(id);
	}

	@Override
	@Transactional
	public void deleteUser(int id) {
		userDao.deleteUser(id);
		
	}

	@Override
	@Transactional
	public List<Donation> getDonations() {
		donationDAO.updateStatus();
		
		return donationDAO.getDonations();
	}

	@Override
	@Transactional
	public void saveDonation(Donation dona) {
		donationDAO.saveDonation(dona);
		
	}

	@Override
	@Transactional
	public Donation getDonation(int id) {
		return donationDAO.getDonation(id);
	}

	@Override
	@Transactional
	public void deleteDonation(int id) {
		donationDAO.deleteDonation(id);
		
	}

	@Override
	@Transactional
	public List<UserDonation> getUserDonations() {
		return userDonationDAO.getUserDonations();
	}

	@Override
	@Transactional
	public UserDonation getUserDonation(int id) {
		return userDonationDAO.getUserDonation(id);
	}

	@Override
	@Transactional
	public void saveUserDonation(UserDonation ud) {
		userDonationDAO.save(ud);
		
	}

	@Override
	@Transactional
	public User login(String username, String password) {
		return userDao.login(username, password);
	}

	@Override
	@Transactional
	public User loginAdmin(String username, String password) {
		return userDao.loginAdmin(username, password);
	}

	@Override
	@Transactional
	public User getUser(String username) {
		return userDao.getUser(username);
	}

	@Override
	@Transactional
	public boolean isValidUsername(String username) {
		return userDao.getUser(username) == null;
	}

	@Override
	@Transactional
	public List<User> getUsers(String searchKey) {
		return userDao.getUsers(searchKey);
	}

	@Override
	@Transactional
	public List<Donation> getDonations(String searchKey) {
		return donationDAO.getDonations(searchKey);
	}

	@Override
	@Transactional
	public Role getRole(String roleName) {
		return roleDao.getRole(roleName);
	}

	@Override
	@Transactional
	public User validOldPassword(String username, String oldPassword) {
		return userDao.validOldPassword(username, oldPassword);
	}
	 

}
