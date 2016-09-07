package ua.nure.marushchenko.SummaryTask4.db.entity;

import java.math.BigDecimal;
import java.util.Date;

import ua.nure.marushchenko.SummaryTask4.db.Entity;

/**
 * Payment entity.
 * 
 * @author Yulia Marushchenko
 */
public class Payment extends Entity {

	private static final long serialVersionUID = 1165828442725408031L;
	
	private BigDecimal ammount;
	
	private int userId;
	
	private int creditCard;
	
	private Date date;
	
	private String statusPayment;

	public BigDecimal getAmmount() {
		return ammount;
	}

	public void setAmmount(BigDecimal ammount) {
		this.ammount = ammount;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(int credirCard) {
		this.creditCard = credirCard;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getStatusPayment() {
		return statusPayment;
	}

	public void setStatusPayment(String statusPayment) {
		this.statusPayment = statusPayment;
	}

	@Override
	public String toString() {
		return "Payment [ammount=" + ammount + ", userId=" + userId + ", creditCard=" + creditCard + ", date=" + date
				+ ", statusPayment=" + statusPayment + "]";
	}

	
	
	
}
