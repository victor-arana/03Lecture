package courses.hibernate.service.impl;


import courses.hibernate.dao.AccountDAO;
import courses.hibernate.dao.impl.AccountDAOHibernate;
import courses.hibernate.service.AccountServiceInterface;
import courses.hibernate.vo.Account;

/**
 * Service layer for Account
 */
public class AccountService implements AccountServiceInterface{
	
	AccountDAO accountDAO = new AccountDAOHibernate();
	
	/**
	 * Create a new account
	 * 
	 * @param account account to be created
	 * @return created account
	 */
	public Account createAccount(Account account) {
		return accountDAO.createAccount(account);
	}

	/**
	 * Update an account
	 * 
	 * @param account account to be created
	 */
	public void updateAccount(Account account) {
		accountDAO.updateAccount(account);
	}

	/**
	 * Retrieve an account
	 * 
	 * @param accountId identifier of the account to be retrieved
	 * @return account represented by the identifier provided
	 */
	public Account getAccount(long accountId) {
		return accountDAO.getAccount(accountId);
	}

	/**
	 * Delete account
	 * 
	 * @param account account to be deleted
	 */
	public void deleteAccount(Account account) {
		accountDAO.deleteAccount(account);
	}
}
