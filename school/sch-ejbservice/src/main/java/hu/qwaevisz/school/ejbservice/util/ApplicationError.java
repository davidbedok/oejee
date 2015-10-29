package hu.qwaevisz.school.ejbservice.util;

import hu.qwaevisz.school.ejbservice.domain.ErrorStub;

public enum ApplicationError {

	UNEXPECTED(10, 500, "Unexpected error"), // Internal Server Error
	NOT_EXISTS(40, 400, "Resource not found"), // Bad Request
	HAS_DEPENDENCY(50, 412, "Has dependency"); // Precondition Failed

	private final int code;
	private final int httpStatusCode;
	private final String message;

	private ApplicationError(int code, int httpStatusCode, String message) {
		this.code = code;
		this.httpStatusCode = httpStatusCode;
		this.message = message;
	}

	public int getHttpStatusCode() {
		return this.httpStatusCode;
	}

	public ErrorStub build(String field) {
		return new ErrorStub(this.code, this.message, field);
	}

}
