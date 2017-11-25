package hu.qwaevisz.school.ejbservice.domain;

import java.util.ArrayList;
import java.util.List;

public class StudentStub {

	private final String name;
	private final String neptun;
	private final InstituteStub institute;
	private final List<MarkStub> marks;

	public StudentStub(String name, String neptun, InstituteStub institute) {
		this.name = name;
		this.neptun = neptun;
		this.institute = institute;
		this.marks = new ArrayList<>();
	}

	public String getName() {
		return this.name;
	}

	public String getNeptun() {
		return this.neptun;
	}

	public InstituteStub getInstitute() {
		return this.institute;
	}

	public List<MarkStub> getMarks() {
		return this.marks;
	}

	public int getNumberOfMarks() {
		return this.marks.size();
	}

	public void addMark(MarkStub stub) {
		this.marks.add(stub);
	}

	@Override
	public String toString() {
		return "StudentStub [name=" + this.name + ", neptun=" + this.neptun + ", institute=" + this.institute + "]";
	}

}
