package courses.hibernate.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import courses.hibernate.dao.AccountDAO;
import courses.hibernate.vo.Account;

public class AccountDAOOracle implements AccountDAO {
	
	public static final String URL = "jdbc:oracle:thin:LECTURE3/reverload@localhost:1521:XE";
	public static final String GET_ACCOUNT_ID = "SELECT HIBERNATE_SEQUENCE.NEXTVAL FROM DUAL";
	public static final String GET_ACCOUNT = "SELECT ACCOUNT_ID, ACCOUNT_TYPE, CREATION_DATE, BALANCE FROM ACCOUNT WHERE ACCOUNT_ID = ?";
	public static final String CREATE_ACCOUNT = "INSERT INTO ACCOUNT(ACCOUNT_ID, ACCOUNT_TYPE, CREATION_DATE, BALANCE) VALUES(?,?,SYSDATE,?)";
	public static final String UPDATE_ACCOUNT = "UPDATE ACCOUNT SET BALANCE = ? WHERE ACCOUNT_ID = ?";
	public static final String DELETE_ACCOUNT = "DELETE FROM ACCOUNT WHERE ACCOUNT_ID = ?";
	
	private Connection getConnection() throws SQLException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		Connection connection = DriverManager.getConnection(URL);
		connection.setAutoCommit(false);
		return connection;
	}
	
	/**
	 * Clean up database resources 
	 * @param connection connection to close
	 * @param statement statement to close
	 * @param resultSet resultSet to close
	 */
	protected void cleanupDatabaseResources(Connection connection,	Statement statement, ResultSet resultSet) {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create a new account
	 * 
	 * @param account account to be created
	 * @return created account
	 */
	public Account createAccount(Account account) {
		Connection connection = null;
		PreparedStatement getAccountIdStatement = null;
		PreparedStatement createAccountStatement = null;
		ResultSet resultSet = null;
		long accountId=0;
		try {
			connection = getConnection();
			getAccountIdStatement = connection.prepareStatement(GET_ACCOUNT_ID);
			resultSet = getAccountIdStatement.executeQuery();
			resultSet.next();
			accountId = resultSet.getLong(1);

			createAccountStatement = connection.prepareStatement(CREATE_ACCOUNT);
			createAccountStatement.setLong(1, accountId);
			createAccountStatement.setString(2, account.getAccountType());
			createAccountStatement.setDouble(3, account.getBalance());
			createAccountStatement.execute();

			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try{
				connection.rollback();
			}catch(SQLException e1){
				throw new RuntimeException(e1);
			}
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			cleanupDatabaseResources(null, getAccountIdStatement, resultSet);
			cleanupDatabaseResources(connection, createAccountStatement, null);
		}
		return getAccount(accountId);
	}

	/**
	 * Update an account
	 * 
	 * @param account
	 *            account to be created
	 */
	public void updateAccount(Account account) {
		Connection connection = null;
		PreparedStatement updateAccountStatement = null;
		try {
			connection = getConnection();
			updateAccountStatement = connection.prepareStatement(UPDATE_ACCOUNT);
			updateAccountStatement.setDouble(1, account.getBalance());
			updateAccountStatement.setLong(2, account.getAccountId());
			updateAccountStatement.execute();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try{
				connection.rollback();
			}catch(SQLException e1){
				e.printStackTrace();
				throw new RuntimeException(e1);
			}
			throw new RuntimeException(e);
		} finally {
			cleanupDatabaseResources(connection, updateAccountStatement, null);
		}

	}
	
	/**
	 * Delete account from data store
	 * 
	 * @param account account to be deleted
	 */
	public void deleteAccount(Account account) {
		Connection connection = null;
		PreparedStatement deleteAccountStatement = null;
		try {
			connection = getConnection();
			deleteAccountStatement = connection.prepareStatement(DELETE_ACCOUNT);
			deleteAccountStatement.setLong(1, account.getAccountId());
			deleteAccountStatement.execute();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try{
				connection.rollback();
			}catch(SQLException e1){
				throw new RuntimeException(e1);
			}
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			cleanupDatabaseResources(connection, deleteAccountStatement, null);
		}
	}

	/**
	 * Retrieve an account from the data store
	 * 
	 * @param accountId identifier of the account to be retrieved
	 * @return account represented by the identifier provided
	 */
	public Account getAccount(long accountId) {
		Connection connection = null;
		PreparedStatement getAccountStatement = null;
		ResultSet resultSet = null;
		try {
			connection = getConnection();
			getAccountStatement = connection.prepareStatement(GET_ACCOUNT);
			getAccountStatement.setLong(1, accountId);
			resultSet = getAccountStatement.executeQuery();

			Account account = null;
			if (resultSet.next()) {
				account = new Account();
				account.setAccountId(resultSet.getLong("ACCOUNT_ID"));
				account.setAccountType(resultSet.getString("ACCOUNT_TYPE"));
				account.setBalance(resultSet.getDouble("BALANCE"));
				account.setCreationDate(resultSet.getDate("CREATION_DATE"));
			}
			connection.commit();
			return account;

		} catch (SQLException e) {
			e.printStackTrace();
			try{
				connection.rollback();
			}catch(SQLException e1){
				throw new RuntimeException(e1);
			}
			throw new RuntimeException(e);
		} finally {
			cleanupDatabaseResources(connection, getAccountStatement, resultSet);
		}	
	}
}
