package courses.hibernate.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import courses.hibernate.dao.AccountDAO;
import courses.hibernate.vo.Account;

@Repository
public class AccountDAOHibernate implements AccountDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	public Account createAccount(Account account) {
		Session session = getSession();
		Account accnt = (Account) session.merge(account);
		return accnt;
	}

	public Account getAccount(long accountId) {
		Session session = getSession();
		Account account = (Account) session.get(Account.class, accountId);
		return account;
	}

	public void deleteAccount(Account account) {
		Session session = getSession();
		session.delete(account);
	}	
	
	public void updateAccount(Account account) {
		Session session = getSession();
		session.saveOrUpdate(account);
	}
	
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

}
