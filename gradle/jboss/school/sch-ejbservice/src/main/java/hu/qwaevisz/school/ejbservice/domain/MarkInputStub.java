package hu.qwaevisz.school.ejbservice.domain;

public class MarkInputStub {

	private final String subject;
	private final String neptun;
	private final Grade grade;
	private final String note;

	public MarkInputStub() {
		this(null, null, null, null);
	}

	public MarkInputStub(String subject, String neptun, Grade grade, String note) {
		super();
		this.subject = subject;
		this.neptun = neptun;
		this.grade = grade;
		this.note = note;
	}

	public String getSubject() {
		return this.subject;
	}

	public String getNeptun() {
		return this.neptun;
	}

	public Grade getGrade() {
		return this.grade;
	}

	public String getNote() {
		return this.note;
	}

	@Override
	public String toString() {
		return "MarkInputStub [subject=" + this.subject + ", neptun=" + this.neptun + ", grade=" + this.grade + ", note=" + this.note + "]";
	}

}
