package hu.qwaevisz.school.ejbservice.exception;

import javax.ws.rs.core.Response.Status;

import hu.qwaevisz.school.ejbservice.domain.ErrorStub;
import hu.qwaevisz.school.ejbservice.util.ApplicationError;

public class AdaptorException extends Exception {

	private static final long serialVersionUID = 1L;

	private final ApplicationError error;
	private final String fields;

	public AdaptorException(ApplicationError error, String message) {
		this(error, message, null);
	}

	public AdaptorException(ApplicationError error, String message, String fields) {
		this(error, message, null, fields);
	}

	public AdaptorException(ApplicationError error, String message, Throwable cause, String fields) {
		super(message, cause);
		this.error = error;
		this.fields = fields;
	}

	public Status getHttpStatus() {
		return this.error.getHttpStatus();
	}

	public ErrorStub build() {
		return this.error.build(this.fields);
	}

}
