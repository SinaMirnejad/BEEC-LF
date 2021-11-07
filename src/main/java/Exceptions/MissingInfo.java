package Exceptions;

import java.io.Serializable;

public class MissingInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String message;
	public MissingInfo(String message) {
		this.message = message;
	}
	
}
