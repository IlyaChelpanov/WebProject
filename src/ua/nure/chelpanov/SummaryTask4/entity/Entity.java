package ua.nure.chelpanov.SummaryTask4.entity;

import java.io.Serializable;

public abstract class Entity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7219454113809534016L;
	private Long ID;

	public Long getID() {
		return ID;
	}

	public void setId(Long ID) {
		this.ID = ID;
	}
}
