package hu.qwaevisz.bookstore.persistence.exception;

public class PersistenceServiceException extends Exception {

	private static final long serialVersionUID = 1207428295818535206L;

	public PersistenceServiceException(String message) {
		super(message);
	}

	public PersistenceServiceException(String message, Throwable cause) {
		super(message, cause);
	}

}