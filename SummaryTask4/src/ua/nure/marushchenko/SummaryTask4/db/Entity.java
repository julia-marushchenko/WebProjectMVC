package ua.nure.marushchenko.SummaryTask4.db;

import java.io.Serializable;

/**
 * Root of all entities which have identifier field. 
 * 
 * @author Yulia Marushchenko
 *
 */
public class Entity implements Serializable{
	
	private static final long serialVersionUID = 1624994459059123076L;
	
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
