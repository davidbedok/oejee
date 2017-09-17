package hu.qwaevisz.webstore.persistence.exception;

public class PersistenceException extends Exception {

	private static final long serialVersionUID = 2081498367156062715L;

	public PersistenceException(String message) {
		this(message, null);
	}

	public PersistenceException(String message, Throwable cause) {
		super(message, cause);
	}

}
