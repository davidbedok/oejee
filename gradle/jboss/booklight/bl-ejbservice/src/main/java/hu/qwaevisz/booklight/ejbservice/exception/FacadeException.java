package hu.qwaevisz.booklight.ejbservice.exception;

public class FacadeException extends Exception {

	private static final long serialVersionUID = 1L;

	public FacadeException(String message) {
		super(message);
	}

	public FacadeException(String message, Throwable cause) {
		super(message, cause);
	}

}
