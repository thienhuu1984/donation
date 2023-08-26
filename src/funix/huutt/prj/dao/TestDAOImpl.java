package funix.huutt.prj.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import funix.huutt.prj.entity.Test;

@Repository
public class TestDAOImpl implements TestDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	
	@Override
	public List<Test> displayTests() {
		
		Session session = sessionFactory.getCurrentSession();
		
		Query<Test> query = session.createQuery("from Test", Test.class);
		
		
		List<Test> list = query.getResultList();
		
		return list;
	}


	@Override
	public void save(Test value) {

		Session session = sessionFactory.getCurrentSession();
		
		session.save(value);
		
	}

}
