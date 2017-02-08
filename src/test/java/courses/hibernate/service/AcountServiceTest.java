package courses.hibernate.service;

import java.util.Date;

import junit.framework.Assert;

import org.junit.Test;

import courses.hibernate.service.impl.AccountService;
import courses.hibernate.vo.Account;

public class AcountServiceTest {
	
	private AccountServiceInterface accountService = new AccountService();
	
	/**
	 * Test account creation
	 */
//	@Test
	public void testCreateAccount(){
		// create the account
		// ------ --- -------
		Account account = buildAccount();
		Assert.assertTrue(account.getAccountId() == 0);
		
		// save the account
		// ---- --- -------
		account = accountService.createAccount(account);
		System.out.println("var account = " + account);
		
		// check that IDs were set after the hbm session
		// ----- ---- --- ---- --- ----- --- --- -------
		Assert.assertTrue(account.getAccountId() > 0);
		
		// cleanup
		// -------
		deleteAccount(account);
	}
	
	/**
	 * Test retrieval of account
	 */	
	@Test
	public void testGetAccount(){
		Account account = createAccount();
		System.out.println("var account = " + account);
		
		Account anotherCopy = accountService.getAccount(account.getAccountId());

		System.out.println("var anotherCopy = " + anotherCopy);

		// make sure these are two separate instances
		// ---- ---- ----- --- --- -------- ---------
		Assert.assertTrue(account != anotherCopy);

		// cleanup
		// -------
//		deleteAccount(account);
		
	}
	
	/**
	 * Test deletion of account
	 */
//	@Test
	public void testDeleteAccount() {
		Account account = createAccount();
		System.out.println("var account = " + account);

		// delete the account
		// ------ --- -------
		accountService.deleteAccount(account);

		// try to get the account again -- should be null
		// --- -- --- --- ------- ----- -- ------ -- ----
		Account anotherCopy = accountService.getAccount(account.getAccountId());

		System.out.println("var anotherCopy = " + anotherCopy);

		Assert.assertNull(anotherCopy);
	}

	
	/**
	 * Test update of account type. Account Type is set to update=false in
	 * Hibernate Mapping. Therefore, ensure that it does not get updated.
	 */
//	@Test
	public void testUpdateAccountType() {
		Account account = createAccount();
		System.out.println("var account = " + account);

		account.setAccountType("Check");
		accountService.updateAccount(account);

		Account anotherCopy = accountService.getAccount(account.getAccountId());
		System.out.println("var anotherCopy = " + anotherCopy);

		// make sure the one we just pulled back from
		// the database DOES NOT HAVE the updated balance
		// ----------------------------------------------
		Assert.assertFalse(anotherCopy.getAccountType().equals("Check"));

		// cleanup
		// -------
		deleteAccount(account);
	}

	/**
	 * Test updating of account balance
	 */
//	@Test
	public void testUpdateAccountBalance() {
		Account account = createAccount();
		System.out.println("var account = " + account);

		account.setBalance(2000);
		accountService.updateAccount(account);

		Account anotherCopy = accountService.getAccount(account.getAccountId());
		System.out.println("var anotherCopy = " + anotherCopy);

		// make sure the one we just pulled back
		// from the database has the updated balance
		// -----------------------------------------
		Assert.assertTrue(anotherCopy.getBalance() == 2000);

		// cleanup
		// -------
		deleteAccount(account);
	}
	
	/**
	 * Build an account
	 * 
	 * @return account
	 */
	private Account buildAccount(){
		Account account = new Account();
		account.setAccountType("Savings");
		account.setCreationDate(new Date());
		account.setBalance(1000L);
		return account;
	}

	/**
	 * Create an account
	 * 
	 * @return account that was created
	 */	
	private Account createAccount(){
		Account account = buildAccount();
		account = accountService.createAccount(account);
		return account;
	}
	
	/**
	 * Delete an account
	 * 
	 * @param account account to be deleted
	 */
	private void deleteAccount(Account account){
		accountService.deleteAccount(account);
	}
	

}
