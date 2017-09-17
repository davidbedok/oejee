package hu.qwaevisz.inventory.ejbservice.exception;

import hu.qwaevisz.inventory.persistence.domain.Error;

public class AdaptorException extends Exception {

	private static final long serialVersionUID = 6791823269155412515L;

	public AdaptorException(String message) {
		super(message);
	}

	public AdaptorException(String message, Throwable cause) {
		super(message, cause);
	}

	public Error build() {
		return new Error(10, this.getMessage());
	}

}
