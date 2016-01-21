package hu.qwaevisz.school.ejbservice.domain;

public class ErrorStub {

	private final int code;

	private final String message;

	private final String fields;

	public ErrorStub() {
		this(0, null, null);
	}

	public ErrorStub(int code, String message, String fields) {
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

	@Override
	public String toString() {
		return "ErrorStub [code=" + this.code + ", message=" + this.message + ", fields=" + this.fields + "]";
	}

}
