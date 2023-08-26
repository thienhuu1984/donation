package funix.huutt.prj.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import funix.huutt.prj.entity.Donation;
import funix.huutt.prj.entity.UserDonation;

@Repository
public class UserDonationDaoImpl implements UserDonationDAO {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<UserDonation> getUserDonations() {
		
		List<UserDonation> list = sessionFactory.getCurrentSession()
									.createQuery("from UserDonation", UserDonation.class)
									.getResultList();
		return list;
	}

	@Override
	public void save(UserDonation ud) {
		
		Session session = sessionFactory.getCurrentSession();
		
		session.saveOrUpdate(ud);
		
		
	}

	@Override
	public UserDonation getUserDonation(int id) {
		
		UserDonation ud = sessionFactory.getCurrentSession()
							.get(UserDonation.class, id);
		return ud;
	}
	
	@Override
	public List<UserDonation> getUserDonations(int donationId) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Query<UserDonation> query = session.createQuery("FROM UserDonation WHERE donation_id = :donationId", UserDonation.class);
		
		query.setParameter("donationId", donationId);
		
		List<UserDonation> lists = query.getResultList();
		
		return lists;
	}

	
}
