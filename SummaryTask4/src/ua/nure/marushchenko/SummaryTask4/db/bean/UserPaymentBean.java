package ua.nure.marushchenko.SummaryTask4.db.bean;

import java.math.BigDecimal;
import java.util.Date;

import ua.nure.marushchenko.SummaryTask4.db.Entity;

/**
 * User payment bean.
 * 
 * @author Yulia Marushchenko
 */
public class UserPaymentBean extends Entity {

	private static final long serialVersionUID = 7690654266731078681L;
	
	
	private int credirCardNumber;
	
	private BigDecimal ammount;
	
	private Date date;
	
	private String statusPayment;
	
	

	public int getCredirCardNumber() {
		return credirCardNumber;
	}



	public void setCredirCardNumber(int credirCardNumber) {
		this.credirCardNumber = credirCardNumber;
	}



	public BigDecimal getAmmount() {
		return ammount;
	}



	public void setAmmount(BigDecimal ammount) {
		this.ammount = ammount;
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
		return "UserPaymentBean [credirCardNumber=" + credirCardNumber + ", ammount=" + ammount + ", date=" + date
				+ ", statusPayment=" + statusPayment + "]";
	}

	
	
	
}
