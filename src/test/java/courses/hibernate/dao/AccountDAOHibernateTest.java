package courses.hibernate.dao;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import courses.hibernate.vo.Account;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:root-context.xml")
@TransactionConfiguration(defaultRollback=true,transactionManager="transactionManager")
@Transactional
public class AccountDAOHibernateTest {
	
	@Autowired
	private AccountDAO accountDAO;

	@Test
	public void testCreateAccount() {
		Account account = buildAccount();
		account = accountDAO.createAccount(account);
		assertTrue(account.getAccountId() > 0);
	}
	
	@Test
	public void testReadAccount(){
		Account account = accountDAO.createAccount(buildAccount());
		Account accountCopy = accountDAO.getAccount(account.getAccountId());
		assertTrue(account==accountCopy);
	}
	
	@Test
	public void testDeleteAccount(){
		Account account = accountDAO.createAccount(buildAccount());
		accountDAO.deleteAccount(account);
		Account accountCopy = accountDAO.getAccount(account.getAccountId());
		assertNull(accountCopy);
	}
	
	@Test
	public void testUpdateAccountBalance(){
		Account account = accountDAO.createAccount(buildAccount());
		account.setBalance(2000);
		accountDAO.updateAccount(account);
		Account accountCopy = accountDAO.getAccount(account.getAccountId());
		assertTrue(accountCopy.getBalance() == 2000);
	}
	
	private Account buildAccount(){
		Account account = new Account();
		account.setAccountType("SAVINGS");
		account.setCreationDate(new Date());
		account.setBalance(1000L);
		return account;
	}

}
