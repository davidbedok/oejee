package hu.qwaevisz.webstore.ejbservice.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ServiceError")
public class ServiceError implements Serializable {

	private static final long serialVersionUID = 6351508957255647272L;

	private final int code;
	private final String message;

	public ServiceError() {
		this(0, null);
	}

	public ServiceError(final int code, final String message) {
		this.code = code;
		this.message = message;
	}

	@XmlElement(name = "code")
	public int getCode() {
		return this.code;
	}

	@XmlElement(name = "message")
	public String getMessage() {
		return this.message;
	}

	@Override
	public String toString() {
		return "ErrorStub [code=" + this.code + ", message=" + this.message + "]";
	}

}
