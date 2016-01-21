package hu.qwaevisz.school.restclient.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "teacher")
public class TeacherStub {

	private String name;
	private String neptun;

	public TeacherStub() {
		this(null, null);
	}

	public TeacherStub(String name, String neptun) {
		this.name = name;
		this.neptun = neptun;
	}

	@XmlElement(name = "name")
	public String getName() {
		return this.name;
	}

	@XmlElement(name = "neptun")
	public String getNeptun() {
		return this.neptun;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNeptun(String neptun) {
		this.neptun = neptun;
	}

	@Override
	public String toString() {
		return "TeacherStub [name=" + this.name + ", neptun=" + this.neptun + "]";
	}

}
