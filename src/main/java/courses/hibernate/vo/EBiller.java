package courses.hibernate.vo;

import java.util.Collection;
import java.util.HashSet;

public class EBiller {
	private long ebillerId;
	private String name;
	private String billingAddress;
	private String phone;
	private Collection<Account> accounts = new HashSet<Account>();
	
	public long getEbillerId() {
		return ebillerId;
	}
	public void setEbillerId(long ebillerId) {
		this.ebillerId = ebillerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBillingAddress() {
		return billingAddress;
	}
	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Collection<Account> getAccounts() {
		return accounts;
	}
	public void setAccounts(Collection<Account> accounts) {
		this.accounts = accounts;
	}
	
	/**
	 * Add account to accounts. Maintain both sides of bidirectional
	 * relationship.
	 * 
	 * @param account
	 *            account to be added
	 */
	public void addAccount(Account account) {
		this.accounts.add(account);
		if (!account.getEbillers().contains(this)) {
			account.addEbiller(this);
		}
	}
	
	/**
	 * Remove account from accounts. Maintain both sides of bidirectional
	 * relationship.
	 * 
	 * @param account
	 *            account to be removed
	 */
	public void removeAccount(Account account) {
		this.accounts.remove(account);
		if (account.getEbillers().contains(this)) {
			account.removeEbiller(this);
		}
	}

	
	

}
