package funix.huutt.prj.dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import funix.huutt.prj.entity.Role;

@Repository
public class RoleDAOImpl implements RoleDAO {

	@Autowired
	SessionFactory sessionFactory; 
	
	
	@Override
	public List<Role> getRoles() {
		
		try {
			Session session = sessionFactory.getCurrentSession();
			
			Query<Role> query = session.createQuery("from Role where roleName != null ", Role.class);
			List<Role> roles = query.getResultList();
			
			return roles;
		}  catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public void saveRole(Role role) {

		Session session = sessionFactory.getCurrentSession();
		
		session.saveOrUpdate(role);
		
	}

	@Override
	public Role getRole(int roleId) {
		
		try {
			return sessionFactory.getCurrentSession()
					.get(Role.class, roleId);
		}  catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public Role getRole(String roleName) {

		try {
			
			Session session = sessionFactory.getCurrentSession();
			
			Query<Role> query = session.createQuery("from Role where roleName = :rolename ", Role.class);
			
			query.setParameter("rolename", roleName);
			
			Role role = query.getSingleResult();
			
			return role;
			
		}  catch (NoResultException e) {
			
			return null;
		}
		
	}

}
