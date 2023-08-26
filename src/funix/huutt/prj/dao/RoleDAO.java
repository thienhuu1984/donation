package funix.huutt.prj.dao;

import java.util.List;

import funix.huutt.prj.entity.Role;

public interface RoleDAO {

	List<Role> getRoles();

	void saveRole(Role role);

	Role getRole(int roleId);

	Role getRole(String roleName);

}
