package funix.huutt.prj.dao;

import java.util.List;


import funix.huutt.prj.entity.User;

public interface UserDAO {

	List<User> getUsers();

	void saveUser(User user);

	User getUser(int id);

	void deleteUser(int id);

	User login(String username, String password);

	User loginAdmin(String username, String password);

	User getUser(String username);

	List<User> getUsers(String searchKey);

	User validOldPassword(String username, String oldPassword);
	

}
