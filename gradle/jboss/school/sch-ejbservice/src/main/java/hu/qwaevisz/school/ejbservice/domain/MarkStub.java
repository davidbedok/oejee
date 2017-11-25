package hu.qwaevisz.school.ejbservice.domain;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "mark")
public class MarkStub {

	private final SubjectStub subject;
	private final Grade grade;
	private final String note;
	private final Date date;

	public MarkStub() {
		this(null, Grade.INSUFFICIENT, null, null);
	}

	public MarkStub(SubjectStub subject, Grade grade, String note, Date date) {
		this.subject = subject;
		this.grade = grade;
		this.note = note;
		this.date = date;
	}

	@XmlElement(name = "subject")
	public SubjectStub getSubject() {
		return this.subject;
	}

	@XmlElement(name = "grade")
	public Grade getGrade() {
		return this.grade;
	}

	@XmlElement(name = "gradeValue")
	public int getGradeValue() {
		return this.grade.getValue();
	}

	@XmlElement(name = "note")
	public String getNote() {
		return this.note;
	}

	@XmlElement(name = "date")
	public Date getDate() {
		return this.date;
	}

	@Override
	public String toString() {
		return "MarkStub [subject=" + this.subject + ", grade=" + this.grade + ", note=" + this.note + ", date=" + this.date + "]";
	}

}
