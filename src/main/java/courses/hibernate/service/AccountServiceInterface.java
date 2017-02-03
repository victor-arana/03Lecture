package courses.hibernate.service;

import courses.hibernate.vo.Account;

public interface AccountServiceInterface {

	public Account createAccount(Account account);

	public Account getAccount(long accountId);

	public void deleteAccount(Account account);

	public void updateAccount(Account account);

}
