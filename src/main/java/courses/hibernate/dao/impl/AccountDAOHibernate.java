package courses.hibernate.dao.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;

import courses.hibernate.dao.AccountDAO;
import courses.hibernate.util.HibernateUtil;
import courses.hibernate.vo.Account;

public class AccountDAOHibernate implements AccountDAO {

	public Account createAccount(Account account) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Long accountId = (Long) session.save(account); 
		tx.commit();
		session.close();
		return getAccount(accountId);
	}


	public void updateAccount(Account account) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(account);
		tx.commit();
		session.close();
	}

	public void deleteAccount(Account account) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.delete(account);
		tx.commit();
		session.close();
	}

	public Account getAccount(long accountId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Account account = (Account) session.get(Account.class, accountId);
		tx.commit();
		session.close();
		return account;
	}

}
