package hu.qwaevisz.lottery.ejbservice.exception;

import hu.qwaevisz.lottery.ejbservice.domain.ErrorStub;

public class AdaptorException extends Exception {

	private static final long serialVersionUID = 1L;

	private final String field;

	public AdaptorException(String message) {
		this(message, null, null);
	}

	public AdaptorException(String message, Throwable cause, String field) {
		super(message, cause);
		this.field = field;
	}

	public ErrorStub build() {
		return new ErrorStub(this.getMessage(), this.field);
	}

}
