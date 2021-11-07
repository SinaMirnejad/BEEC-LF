package Exceptions;

public class IncompleteEmployeeInfoException extends Exception {

	
	String message;
	public IncompleteEmployeeInfoException(String in) {
		message = in;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public String getMessage() {
		return message;
	}
}
