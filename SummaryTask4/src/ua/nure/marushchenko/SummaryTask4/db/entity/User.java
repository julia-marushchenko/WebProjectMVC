package ua.nure.marushchenko.SummaryTask4.db.entity;

import ua.nure.marushchenko.SummaryTask4.db.Entity;

/**
 * User entity.
 * 
 * @author Yulia Marushchenko
 *
 */
public class User extends Entity{

	private static final long serialVersionUID = 8362521483425819033L;
	
	private String login;
	
	private String password;
	
	private String firstName;
	
	private String lastName;
	
	private byte roleId;
	
	private int cardId;
	
	private String status;
	private int cardNumber;

	public int getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public byte getRoleId() {
		return roleId;
	}

	public void setRoleId(byte i) {
		this.roleId = i;
	}

	public int getCardId() {
		return cardId;
	}

	public void setCardId(int cardId) {
		this.cardId = cardId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "User [login=" + login + ", password=" + password + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", roleId=" + roleId + ", cardId=" + cardId + ", status=" + status + "]";
	}

	
	
	
}
