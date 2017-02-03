package courses.hibernate.service;

import java.util.Date;

import junit.framework.Assert;

import org.junit.Test;

import courses.hibernate.service.impl.AccountService;
import courses.hibernate.service.impl.EBillerService;
import courses.hibernate.vo.Account;
import courses.hibernate.vo.EBiller;

public class EBillerServiceTest {
	
	private EBillerServiceInterface eBillerService = new EBillerService();
	private AccountServiceInterface accountService = new AccountService();
	
	/**
	 * Build an ebiller
	 * 
	 * @return ebiller
	 */	
	private EBiller buildEBiller(){
		EBiller eBiller = new EBiller();
		eBiller.setName("DISCOVER CARD");
		eBiller.setBillingAddress("500 Madison Avenue NY, NY 10015");
		eBiller.setPhone("1-800-DISCOVER");
		return eBiller;
	}
	
	private EBiller createEBiller(){
		EBiller eBiller = buildEBiller();
		eBillerService.createEBiller(eBiller);
		return eBiller;
	}
	
	private Account buildAccount(){
		Account account = new Account();
		account.setAccountType(Account.ACCOUNT_TYPE_SAVINGS);
		account.setCreationDate(new Date());
		account.setBalance(1000L);
		return account;
	}
	
	@Test
	public void testManyToManyAccountEBillers(){
		//Create EBillers and Accounts
		//------ -------- --- --------
		EBiller ebiller1 = createEBiller();
		EBiller ebiller2 = createEBiller();
		
		Account account1 = buildAccount();
		account1.addEbiller(ebiller1);
		
		Account account2 = buildAccount();
		account2.addEbiller(ebiller1);
		account2.addEbiller(ebiller2);
		
		accountService.createAccount(account1);
		accountService.createAccount(account2);
		
		//Load EBiller and ensure counts of accounts are correct
		//---- ------- --- ------ ------ -- -------- --- -------
		ebiller1 = eBillerService.getEBiller(ebiller1.getEbillerId());
		ebiller2 = eBillerService.getEBiller(ebiller2.getEbillerId());
		Assert.assertEquals(2, ebiller1.getAccounts().size());
		Assert.assertEquals(1, ebiller2.getAccounts().size());
		
		//Load Accounts and ensure counts of ebillers are correct
		//---- -------- --- ------ ------ -- -------- --- -------
		account1 = accountService.getAccount(account1.getAccountId());
		account2 = accountService.getAccount(account2.getAccountId());
		Assert.assertEquals(1, account1.getEbillers().size());
		Assert.assertEquals(2, account2.getEbillers().size());
		
		System.out.println(ebiller1);
		System.out.println(ebiller2);
		System.out.println(account1);
		System.out.println(account2);
		
		// cleanup
		// -------
		deleteAccount(account1);
		deleteAccount(account2);
		deleteEBiller(ebiller1);
		deleteEBiller(ebiller2);
	}
	
	private void deleteAccount(Account account){
		accountService.deleteAccount(account);
	}
	
	private void deleteEBiller(EBiller eBiller){
		eBillerService.delete(eBiller);
	}

}
