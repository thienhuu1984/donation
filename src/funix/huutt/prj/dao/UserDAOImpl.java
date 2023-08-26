package funix.huutt.prj.dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import funix.huutt.prj.entity.User;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<User> getUsers() {
		
		try {
			Session session = sessionFactory.getCurrentSession();
			
			Query<User> query = session.createQuery("from User", User.class);
			
			List<User> users = query.getResultList();
					
			return users;
			
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public void saveUser(User user) {

		Session session = sessionFactory.getCurrentSession();
		
		session.saveOrUpdate(user);
		
	}

	@Override
	public User getUser(int id) {
		
		try {
			User user = sessionFactory
						.getCurrentSession()
						.get(User.class, id);
			return user;
			
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public void deleteUser(int id) {

		Session session = sessionFactory.getCurrentSession();
		
		Query query = session.createQuery("delete from User where id=:userId");
		
		query.setParameter("userId", id);
		
		query.executeUpdate();
		
	}

	@Override
	public User login(String username, String password) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Query<User> query = session.createQuery("FROM User WHERE username = :user_name AND password = :pass_word", User.class);
		
		query.setParameter("user_name", username);
		query.setParameter("pass_word", password);
		

		try {
			return query.getSingleResult();
			
		} catch (NoResultException e) {
			return null;
		}
		
	}

	@Override
	public User loginAdmin(String username, String password) {
		Session session = sessionFactory.getCurrentSession();
		
		Query<User> query = session.createQuery("FROM User WHERE username = :user_name AND password = :pass_word", User.class);
		
		query.setParameter("user_name", username);
		query.setParameter("pass_word", password);
		
		try {
			
			User user = query.getSingleResult();
			if(!user.getRole().getRoleName().toLowerCase().equals("admin"))
				return null;
			return user;
			
		} catch (NoResultException e) {
			
			return null;
		}
		
		
	}

	@Override
	public User getUser(String username) {
			
		try {
			Session session = sessionFactory.getCurrentSession();
			
			Query<User> query = session.createQuery("from User where username=:un", User.class);
			
			query.setParameter("un", username);
			
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
		
	}

	@Override
	public List<User> getUsers(String searchKey) {

		Session session = sessionFactory.getCurrentSession();
		
		Query<User> query = session.createQuery("from User where username like :un", User.class);
		
		query.setParameter("un", "%" + searchKey + "%");
		
		List<User> list;
		
		try {
			list = query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
		
		return list;
		
	}

	@Override
	public User validOldPassword(String username, String oldPassword) {
		try {
			Session session = sessionFactory.getCurrentSession();
			
			Query<User> query = session.createQuery("from User where username=:un and password = :old", User.class);
			
			query.setParameter("un", username);
			query.setParameter("old", oldPassword);
			
			return query.getSingleResult();
			
		} catch (NoResultException e) {
			return null;
		}
	}


}
