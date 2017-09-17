package hu.qwaevisz.lottery.ejbservice.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorStub {

	@XmlElement
	private final String message;

	@XmlElement
	private final String fields;

	public ErrorStub() {
		this(null, null);
	}

	public ErrorStub(String message, String fields) {
		this.message = message;
		this.fields = fields;
	}

	public String getMessage() {
		return this.message;
	}

	public String getFields() {
		return this.fields;
	}

	@Override
	public String toString() {
		return "ErrorStub [message=" + this.message + ", fields=" + this.fields + "]";
	}

}
