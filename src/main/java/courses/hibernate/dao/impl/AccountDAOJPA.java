package courses.hibernate.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import courses.hibernate.dao.AccountDAO;
import courses.hibernate.vo.Account;

public class AccountDAOJPA implements AccountDAO{

	@Override
	public Account createAccount(Account account) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("lecture03PU");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(account);;
		tx.commit();
		em.close();
		return getAccount(account.getAccountId());
	}

	@Override
	public void updateAccount(Account account) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("lecture03PU");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.merge(account);
		tx.commit();
		em.close();
	}

	@Override
	public void deleteAccount(Account account) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("lecture03PU");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.remove(account);
		tx.commit();
		em.close();
	}

	@Override
	public Account getAccount(long accountId) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("lecture03PU");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Account account = em.find(Account.class, accountId);
		tx.commit();
		em.close();
		return account;
	}

}
