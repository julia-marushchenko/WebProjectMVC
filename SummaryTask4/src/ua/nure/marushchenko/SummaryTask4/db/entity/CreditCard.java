package ua.nure.marushchenko.SummaryTask4.db.entity;

import ua.nure.marushchenko.SummaryTask4.db.Entity;

/**
 * Credit card entity.
 * 
 * @author Yulia Marushchenko
 */
public class CreditCard extends Entity{

	private static final long serialVersionUID = -4396081655012784382L;
	
	private int cardNumber;
	
	private int accountId;

	public int getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	@Override
	public String toString() {
		return "CreditCard [cardNumber=" + cardNumber + ", accountId=" + accountId + "]";
	}
	
	
}
