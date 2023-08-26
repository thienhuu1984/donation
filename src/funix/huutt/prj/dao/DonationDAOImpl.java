package funix.huutt.prj.dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import funix.huutt.prj.entity.Donation;


@Repository
public class DonationDAOImpl implements DonationDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public List<Donation> getDonations() {
		
		try {
			Session session = sessionFactory.getCurrentSession();
			
			Query<Donation> query = session.createQuery("from Donation", Donation.class); 
			
			List<Donation> donations = query.getResultList();
			
			return donations;
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public void updateStatus() {

		List<Donation> donations = getDonations();
		
		for(Donation donation: donations) {
			donation.updateStatus();
			sessionFactory.getCurrentSession().saveOrUpdate(donation);
		}
		
	}

	@Override
	public void saveDonation(Donation dona) {
		
		sessionFactory.getCurrentSession()
			.saveOrUpdate(dona);
		
	}

	@Override
	public Donation getDonation(int id) {
		try {
			return sessionFactory.getCurrentSession()
					.get(Donation.class, id);
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public void deleteDonation(int id) {
		Session session = sessionFactory.getCurrentSession();
		
		Query query = session.createQuery("delete from Donation where id=:donationId");
		
		query.setParameter("donationId", id);
		
		query.executeUpdate();
		
	}

	@Override
	public List<Donation> getDonations(String searchKey) {
		
		try {
			
			Session session = sessionFactory.getCurrentSession();
			
			Query<Donation> query = session.createQuery("from Donation where name like :key", Donation.class); 
			
			query.setParameter("key", "%" + searchKey + "%");
			
			List<Donation> donations = query.getResultList();
			
			return donations;
			
		} catch (NoResultException e) {
			return null;
		}
	}
	
	

}
