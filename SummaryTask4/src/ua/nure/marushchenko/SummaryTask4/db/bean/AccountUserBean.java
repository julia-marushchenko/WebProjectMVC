package ua.nure.marushchenko.SummaryTask4.db.bean;

import java.math.BigDecimal;
import java.math.BigInteger;

import ua.nure.marushchenko.SummaryTask4.db.Entity;

/**
 * Bean for account.
 * 
 * @author Yulia Marushchenko
 *
 */
public class AccountUserBean extends Entity{
	
	private static final long serialVersionUID = -3274195294919869649L;
	private int cardNumber;
	private BigDecimal ammount;
	private String status;
	
	public int getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}
	public BigDecimal getAmmount() {
		return ammount;
	}
	public void setAmmount(BigDecimal ammount) {
		this.ammount = ammount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "AccountUserBean [cardNumber=" + cardNumber + ", ammount=" + ammount + ", status=" + status + "]";
	}
	
	
	
}
