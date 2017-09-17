package hu.qwaevisz.diskstore.persistence.exception;

public class PersistenceServiceException extends Exception {

	private static final long serialVersionUID = -6149804497371556366L;

	public PersistenceServiceException(String message) {
		super(message);
	}

	public PersistenceServiceException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
