package hu.qwaevisz.school.ejbservice.util;

import javax.ws.rs.core.Response.Status;

import hu.qwaevisz.school.ejbservice.domain.ErrorStub;

public enum ApplicationError {

	UNEXPECTED(10, Status.INTERNAL_SERVER_ERROR, "Unexpected error"),
	NOT_EXISTS(40, Status.BAD_REQUEST, "Resource not found"),
	HAS_DEPENDENCY(50, Status.PRECONDITION_FAILED, "Has dependency");

	private final int code;
	private final Status httpStatus;
	private final String message;

	private ApplicationError(int code, Status httpStatus, String message) {
		this.code = code;
		this.httpStatus = httpStatus;
		this.message = message;
	}

	public Status getHttpStatus() {
		return this.httpStatus;
	}

	public int getHttpStatusCode() {
		return this.httpStatus.getStatusCode();
	}

	public ErrorStub build(String fields) {
		return new ErrorStub(this.code, this.message, fields);
	}

}
