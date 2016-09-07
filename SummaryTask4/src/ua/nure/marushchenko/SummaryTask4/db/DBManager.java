package ua.nure.marushchenko.SummaryTask4.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import ua.nure.marushchenko.SummaryTask4.db.bean.AccountUserBean;
import ua.nure.marushchenko.SummaryTask4.db.entity.Payment;
import ua.nure.marushchenko.SummaryTask4.db.entity.User;
import ua.nure.marushchenko.SummaryTask4.exception.DBException;
import ua.nure.marushchenko.SummaryTask4.exception.Messages;

/**
 * DB manager. Works with MySQL DB. Only the required DAO methods are defined.
 * 
 * @author Yulia Marushchenko
 * 
 */
public final class DBManager {

	private static final Logger LOG = Logger.getLogger(DBManager.class);

	// //////////////////////////////////////////////////////////
	// singleton
	// //////////////////////////////////////////////////////////

	private static DBManager instance;

	public static synchronized DBManager getInstance() throws DBException {
		if (instance == null) {
			instance = new DBManager();
		}
		return instance;
	}

	private DBManager() throws DBException {
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			ds = (DataSource) envContext.lookup("jdbc/Summary_Task4_db");
			LOG.trace("Data source ==> " + ds);
		} catch (NamingException ex) {
			LOG.error(Messages.ERR_CANNOT_OBTAIN_DATA_SOURCE, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_DATA_SOURCE, ex);
		}
	}

	private DataSource ds;

	// //////////////////////////////////////////////////////////
	// SQL queries
	// //////////////////////////////////////////////////////////

	private static final String SQL_FIND_USER_BY_LOGIN = "SELECT * FROM users WHERE login=?";

	private static final String SQL_CREATE_USER = "INSERT INTO users (`login`, `password`, `first_name`, `last_name`, `role_id`) VALUES (?, ?, ?, ?, ? )";

	private static final String SQL_FIND_USER_ACCOUNTS = "SELECT card_number, balance, account.status FROM credit_card, account, users WHERE users.login = ? AND users.card_id=credit_card.id AND account.id=credit_card.account_id;";

	private static final String SQL_FIND_USER_PAYMENTS = "SELECT amount, card_number, date, payment.status FROM users, payment, credit_card WHERE users.login=? AND payment.user_id=users.id AND credit_card.id=payment.credit_card_id;";

	private static final String SQL_BLOCK_CARD = "UPDATE account SET status='blocked' WHERE id = ?;";

	private static final String SQL_FIND_ACCOUNT_ID_BY_CARD = "SELECT account_id FROM credit_card WHERE card_number=?;";

	private static final String SQL_TAKE_MONEY = "UPDATE account SET balance=balance-? WHERE id = ?;";

	private static final String SQL_SEND_MONEY = "UPDATE forpayment SET amount=amount+? WHERE id=1;";

	private static final String SQL_FIND_ALL_USERS = "SELECT * FROM users;";
	
	private static final String SQL_BLOCK_USER = "UPDATE users SET status='blocked' WHERE login = ?;";
	
	private static final String SQL_UNBLOCK_USER = "UPDATE users SET status='unblocked' WHERE login = ?;";
	
	private static final String SQL_GET_CARD_NUMBER = "SELECT card_number FROM credit_card where id = ?;";

	/**
	 * Returns a DB connection from the Pool Connections.
	 * 
	 * @return DB connection.
	 */
	public Connection getConnection() throws DBException {
		Connection con = null;
		try {
			con = ds.getConnection();
		} catch (SQLException ex) {
			LOG.error(Messages.ERR_CANNOT_OBTAIN_CONNECTION, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_CONNECTION, ex);
		}
		return con;
	}

	/**
	 * Creates new user in DB.
	 * 
	 * @return List of category entities.
	 */
	public boolean createUser(User u) throws DBException {
		boolean created = false;
		User user = u;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_CREATE_USER);
			pstmt.setString(1, user.getLogin());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getFirstName());
			pstmt.setString(4, user.getLastName());
			pstmt.setInt(5, 2);
			pstmt.executeUpdate();
			con.commit();
			created = true;
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_CREATE_USER, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return created;
	}

	/*
	 * Returns all user accounts.
	 * 
	 * @return List of user accounts.
	 */
	public List<AccountUserBean> findUserAccounts(String userLogin) throws DBException {
		List<AccountUserBean> accounts = new ArrayList<AccountUserBean>();
		String login = userLogin;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_FIND_USER_ACCOUNTS);
			pstmt.setString(1, login);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				accounts.add(extractAccounts(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_OBTAIN_USER_ACCOUNTS, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_USER_ACCOUNTS, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return accounts;
	}

	public List<Payment> findUserPayments(String userLogin) throws DBException {
		List<Payment> payments = new ArrayList<Payment>();
		String login = userLogin;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_FIND_USER_PAYMENTS);
			pstmt.setString(1, login);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				payments.add(extractPayments(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_OBTAIN_USER_PAYMENTS, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_USER_PAYMENTS, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return payments;
	}

	public boolean blockingCard(int card) throws DBException {
		int cardNumber = card;
		boolean blocked = false;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		int accountId = findAccountId(cardNumber);
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_BLOCK_CARD);
			pstmt.setInt(1, accountId);
			pstmt.executeUpdate();
			con.commit();
			blocked = true;
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_BLOCK_CARD, ex);
			throw new DBException(Messages.ERR_CANNOT_BLOCK_CARD, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return blocked;
	}
	
	public boolean blockingUser(String login) throws DBException {
		
		boolean blocked = false;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_BLOCK_USER);
			pstmt.setString(1, login);
			pstmt.executeUpdate();
			con.commit();
			blocked = true;
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_BLOCK_USER, ex);
			throw new DBException(Messages.ERR_CANNOT_BLOCK_CARD, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return blocked;
	}
	
public boolean unblockingUser(String login) throws DBException {
		
		boolean unblocked = false;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_UNBLOCK_USER);
			pstmt.setString(1, login);
			pstmt.executeUpdate();
			con.commit();
			unblocked = true;
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_UNBLOCK_USER, ex);
			throw new DBException(Messages.ERR_CANNOT_UNBLOCK_USER, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return unblocked;
	}

	public int findAccountId(int card) throws DBException {
		int cardNumber = card;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		int acId = 0;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_FIND_ACCOUNT_ID_BY_CARD);
			pstmt.setInt(1, cardNumber);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				acId = rs.getInt(Fields.CARD_ACCOUNT_ID);
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_OBTAIN_ACCOUNT_ID, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_ACCOUNT_ID, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return acId;
	}

	/**
	 * Returns a user with the given login.
	 * 
	 * @param login
	 *            User login.
	 * @return User entity.
	 * @throws DBException
	 */
	public User findUserByLogin(String login) throws DBException {
		User user = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_FIND_USER_BY_LOGIN);
			pstmt.setString(1, login);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				user = extractUser(rs);
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_USER_BY_LOGIN, ex);
		} finally {
			close(con, pstmt, rs);
		}

		return user;
	}

	
	
	public int findCardById(int id) throws DBException {
		int cardNumber= 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_GET_CARD_NUMBER);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				cardNumber = rs.getInt("card_number");
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_CARD_BY_ID, ex);
		} finally {
			close(con, pstmt, rs);
		}

		return cardNumber;
	}
	
	public boolean makePayment(int amount, int account, int card) throws DBException {
		
		boolean paid = false;
		int accountId = findAccountId(card);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			
			con = getConnection();
			pstmt = con.prepareStatement(SQL_TAKE_MONEY);
			pstmt.setInt(1, amount);
			pstmt.setInt(2, accountId);
			pstmt.executeUpdate();
			con.commit();
			sendMoney(amount, account);
			paid = true;
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_TAKE_MONEY, ex);
			throw new DBException(Messages.ERR_CANNOT_TAKE_MONEY, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return paid;
	}

	public boolean sendMoney(int amount, int account) throws DBException {

		boolean sent = false;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_SEND_MONEY);
			pstmt.setInt(1, amount);
			pstmt.executeUpdate();
			con.commit();
			sent = true;
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_SEND_MONEY, ex);
			throw new DBException(Messages.ERR_CANNOT_TAKE_MONEY, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return sent;
	}
	
	
	public List<User> findUsers() throws DBException {
		List<User> users = new ArrayList<User>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_FIND_ALL_USERS);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				users.add(extractUser(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_OBTAIN_USERS, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_USERS, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return users;
	}

	// //////////////////////////////////////////////////////////
	// DB util methods
	// //////////////////////////////////////////////////////////

	/**
	 * Closes a connection.
	 * 
	 * @param con
	 *            Connection to be closed.
	 */
	private void close(Connection con) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException ex) {
				LOG.error(Messages.ERR_CANNOT_CLOSE_CONNECTION, ex);
			}
		}
	}

	/**
	 * Closes a statement object.
	 */
	private void close(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException ex) {
				LOG.error(Messages.ERR_CANNOT_CLOSE_STATEMENT, ex);
			}
		}
	}

	/**
	 * Closes a result set object.
	 */
	private void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException ex) {
				LOG.error(Messages.ERR_CANNOT_CLOSE_RESULTSET, ex);
			}
		}
	}

	/**
	 * Closes resources.
	 */
	private void close(Connection con, Statement stmt, ResultSet rs) {
		close(rs);
		close(stmt);
		close(con);
	}

	/**
	 * Rollbacks a connection.
	 * 
	 * @param con
	 *            Connection to be rollbacked.
	 */
	private void rollback(Connection con) {
		if (con != null) {
			try {
				con.rollback();
			} catch (SQLException ex) {
				LOG.error("Cannot rollback transaction", ex);
			}
		}
	}

	// //////////////////////////////////////////////////////////
	// Other methods
	// //////////////////////////////////////////////////////////

	private User extractUser(ResultSet rs) throws SQLException {
		User user = new User();
		user.setId(rs.getLong(Fields.ENTITY_ID));
		user.setLogin(rs.getString(Fields.USER_LOGIN));
		user.setPassword(rs.getString(Fields.USER_PASSWORD));
		user.setFirstName(rs.getString(Fields.USER_FIRST_NAME));
		user.setLastName(rs.getString(Fields.USER_LAST_NAME));
		user.setRoleId(rs.getByte(Fields.USER_ROLE_ID));
		user.setCardId(rs.getInt(Fields.USER_CARD_ID));
		user.setStatus(rs.getString(Fields.USER_STATUS));
		return user;
	}

	/*
	 * Extracts an order entity from the result set.
	 * 
	 * @param rs Result set from which an order entity will be extracted.
	 * 
	 * @return
	 */

	private Payment extractPayments(ResultSet rs) throws SQLException {
		Payment payment = new Payment();
		payment.setAmmount(rs.getBigDecimal(Fields.PAYMENT_AMOUNT));
		payment.setCreditCard(rs.getInt(Fields.CREDITCARD_NUMBER));
		payment.setDate(rs.getDate(Fields.PAYMENT_DATE));
		// payment.setStatusPayment((String)rs.getObject(Fields.PAYMENT_STATUS));
		payment.setStatusPayment("sent");
		return payment;
	}

	/*
	 * Extracts accounts from the result set.
	 * 
	 * @param rs Result set from which a accounts entity will be extracted.
	 * 
	 * @return account entity.
	 */
	private AccountUserBean extractAccounts(ResultSet rs) throws SQLException {
		AccountUserBean account = new AccountUserBean();
		account.setAmmount(rs.getBigDecimal(Fields.ACCOUNT_BALANCE));
		account.setCardNumber(rs.getInt(Fields.CARD_NUMBER));
		account.setStatus(rs.getString(Fields.ACCOUNT_STATUS));
		return account;
	}

}