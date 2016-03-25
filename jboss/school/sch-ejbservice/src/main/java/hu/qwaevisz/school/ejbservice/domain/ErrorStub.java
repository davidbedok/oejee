package hu.qwaevisz.school.ejbservice.domain;

public class ErrorStub {

	private int code;

	private String message;

	private String fields;

	public ErrorStub() {
		this(0, null, null);
	}

	public ErrorStub(final int code, final String message, final String fields) {
		this.code = code;
		this.message = message;
		this.fields = fields;
	}

	public int getCode() {
		return this.code;
	}

	public String getMessage() {
		return this.message;
	}

	public String getFields() {
		return this.fields;
	}

	public void setCode(final int code) {
		this.code = code;
	}

	public void setMessage(final String message) {
		this.message = message;
	}

	public void setFields(final String fields) {
		this.fields = fields;
	}

	@Override
	public String toString() {
		return "ErrorStub [code=" + this.code + ", message=" + this.message + ", fields=" + this.fields + "]";
	}

}
