package hu.qwaevisz.school.restclient.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "subject")
public class SubjectStub {

	private String name;
	private TeacherStub teacher;
	private String description;

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

	public void setName(String name) {
		this.name = name;
	}

	public void setTeacher(TeacherStub teacher) {
		this.teacher = teacher;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "SubjectStub [name=" + this.name + ", teacher=" + this.teacher + ", description=" + this.description + "]";
	}

}
