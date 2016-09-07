package ua.nure.marushchenko.SummaryTask4.db.entity;

import java.math.BigDecimal;

import ua.nure.marushchenko.SummaryTask4.db.Entity;

/**
 * Account entity.
 * 
 * @author Yulia Marushchenko
 */
public class Account extends Entity{

	private static final long serialVersionUID = -4990130378190654527L;
	
	private BigDecimal balance;
	
	private String status;

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Account [balance=" + balance + ", status=" + status + "]";
	}	
	
}
