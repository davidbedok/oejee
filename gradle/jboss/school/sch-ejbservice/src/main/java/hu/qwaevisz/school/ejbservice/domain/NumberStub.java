package hu.qwaevisz.school.ejbservice.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "number")
public class NumberStub {

	private int value;

	public NumberStub() {
		this(0);
	}

	public NumberStub(int value) {
		this.value = value;
	}

	@XmlElement(name = "value")
	public int getValue() {
		return this.value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}
