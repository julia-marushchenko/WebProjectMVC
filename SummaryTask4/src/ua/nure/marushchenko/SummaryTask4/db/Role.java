package ua.nure.marushchenko.SummaryTask4.db;

import ua.nure.marushchenko.SummaryTask4.db.entity.User;

/**
 * Role entity.
 * 
 * @author Yulia Marushchenko
 * 
 */

public enum Role {
	ADMIN, CLIENT;
	
	public static Role getRole(User user) {
		int roleId = user.getRoleId() - 1;
		return Role.values()[roleId];
	}
	
	public String getName() {
		return name().toLowerCase();
	}
	
}
