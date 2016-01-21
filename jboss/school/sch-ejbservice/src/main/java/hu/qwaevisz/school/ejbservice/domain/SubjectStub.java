package hu.qwaevisz.school.ejbservice.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "subject")
public class SubjectStub {

	private final String name;
	private final TeacherStub teacher;
	private final String description;

	public SubjectStub() {
		this(null, null, null);
	}

	public SubjectStub(String name, TeacherStub teacher, String description) {
		this.name = name;
		this.teacher = teacher;
		this.description = description;
	}

	@XmlElement(name = "name")
	public String getName() {
		return this.name;
	}

	@XmlElement(name = "teacher")
	public TeacherStub getTeacher() {
		return this.teacher;
	}

	@XmlElement(name = "description")
	public String getDescription() {
		return this.description;
	}

	@Override
	public String toString() {
		return "SubjectStub [name=" + this.name + ", teacher=" + this.teacher + ", description=" + this.description + "]";
	}

}
