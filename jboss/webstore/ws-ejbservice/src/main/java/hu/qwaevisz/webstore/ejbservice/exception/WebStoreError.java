package hu.qwaevisz.webstore.ejbservice.exception;

public enum WebStoreError {

	IDENTIFIER(10),
	PERSISTENCE(20),
	PRODUCT(30);

	private int code;

	private WebStoreError(int code) {
		this.code = code;
	}

	public int getCode() {
		return this.code;
	}

}
