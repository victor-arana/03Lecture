package courses.hibernate.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import courses.hibernate.dao.AccountDAO;
import courses.hibernate.vo.Account;

@Repository
public class AccountDAOJPA implements AccountDAO{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Account createAccount(Account account){
		manager.persist(account);
		return getAccount(account.getAccountId());
	}
	
	@Override
	public Account getAccount(long accountId) {
		Account account = manager.find(Account.class, accountId);
		return account;
	}

	@Override
	public void updateAccount(Account account) {
		manager.merge(account);	
	}

	@Override
	public void deleteAccount(Account account) {
		manager.remove(account);
	}
	
}
