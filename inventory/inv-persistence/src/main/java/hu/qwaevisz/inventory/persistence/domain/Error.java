package hu.qwaevisz.inventory.persistence.domain;

public class Error {

	private final int code;
	private final String message;

	public Error() {
		this(0, null);
	}

	public Error(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return this.code;
	}

	public String getMessage() {
		return this.message;
	}

	@Override
	public String toString() {
		return "Error [code=" + this.code + ", message=" + this.message + "]";
	}

}
