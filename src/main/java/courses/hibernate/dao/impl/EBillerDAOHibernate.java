package courses.hibernate.dao.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;

import courses.hibernate.dao.EBillerDAO;
import courses.hibernate.util.HibernateUtil;
import courses.hibernate.vo.EBiller;

public class EBillerDAOHibernate implements EBillerDAO {

	@Override
	public EBiller createEBiller(EBiller eBiller) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Long eBillerId = (Long) session.save(eBiller);
		tx.commit();
		session.close();
		return getEBiller(eBillerId);
	}

	@Override
	public EBiller getEBiller(long ebillerId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		EBiller eBiller = (EBiller) session.get(EBiller.class, ebillerId);
		tx.commit();
		session.close();				
		return eBiller;
	}

	@Override
	public void deleteEBiller(EBiller eBiller) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.delete(eBiller);
		tx.commit();
		session.close();
	}

}
