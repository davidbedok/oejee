package hu.qwaevisz.magazine.ejbservice.exception;

public class FacadeException extends Exception {

	private static final long serialVersionUID = 6791823269155412515L;

	public FacadeException(String message) {
		super(message);
	}

	public FacadeException(String message, Throwable cause) {
		super(message, cause);
	}

}
