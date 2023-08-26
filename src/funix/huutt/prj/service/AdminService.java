package funix.huutt.prj.service;

import java.util.List;


import funix.huutt.prj.entity.Donation;
import funix.huutt.prj.entity.Role;
import funix.huutt.prj.entity.User;
import funix.huutt.prj.entity.UserDonation;

public interface AdminService {

	List<Role> getRoles();

	void saveRole(Role role);

	List<User> getUsers();

	void saveUser(User newUser);

	Role getRole(int roleId);

	User getUser(int id);

	void deleteUser(int id);

	List<Donation> getDonations();

	void saveDonation( Donation dona);

	Donation getDonation(int id);

	void deleteDonation(int id);

	List<UserDonation> getUserDonations();

	UserDonation getUserDonation(int id);

	void saveUserDonation(UserDonation ud);

	User login(String username, String password);

	User loginAdmin(String username, String password);

	User getUser(String username);

	boolean isValidUsername(String username);

	List<User> getUsers(String searchKey);

	List<Donation> getDonations(String searchKey);

	Role getRole(String roleName);

	User validOldPassword(String username, String oldPassword);

}
