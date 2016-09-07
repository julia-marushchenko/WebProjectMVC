package ua.nure.marushchenko.SummaryTask4.exception;

/**
 * Holder for messages of exceptions.
 * 
 * @author Yulia Marushchenko
 *
 */
public class Messages {

	private Messages() {
		// no op
	}
	
	public static final String ERR_CANNOT_CREATE_USER = "Cannot create user";
	
	public static final String ERR_CANNOT_OBTAIN_USER_ACCOUNTS = "Cannot obtain user accounts";
	
	public static final String ERR_CANNOT_OBTAIN_USER_PAYMENTS = "Cannot obtain user payments";
	
	public static final String ERR_CANNOT_OBTAIN_ACCOUNT_ID = "Cannot obtain account id";

	public static final String ERR_CANNOT_OBTAIN_CONNECTION = "Cannot obtain a connection from the pool";
	
	public static final String ERR_CANNOT_BLOCK_CARD = "Cannot block card";
	
	public static final String ERR_CANNOT_UNBLOCK_USER = "Cannot unblock user";
	
	public static final String ERR_CANNOT_BLOCK_USER = "Cannot block user";
	
	public static final String ERR_CANNOT_TAKE_MONEY ="Cannot take money";
	
	public static final String  ERR_CANNOT_SEND_MONEY = "CAnnot send money";

	public static final String ERR_CANNOT_OBTAIN_USERS ="Cannot obtain users";

	public static final String ERR_CANNOT_OBTAIN_USER_BY_ID = "Cannot obtain a user by its id";

	public static final String ERR_CANNOT_OBTAIN_USER_BY_LOGIN = "Cannot obtain a user by its login";

	public static final String ERR_CANNOT_UPDATE_USER = "Cannot update a user";

	public static final String ERR_CANNOT_CLOSE_CONNECTION = "Cannot close a connection";

	public static final String ERR_CANNOT_CLOSE_RESULTSET = "Cannot close a result set";

	public static final String ERR_CANNOT_CLOSE_STATEMENT = "Cannot close a statement";

	public static final String ERR_CANNOT_OBTAIN_DATA_SOURCE = "Cannot obtain the data source";
	
	public static final String ERR_CANNOT_CARD_BY_ID = "Cannot obtain card number";
	
}