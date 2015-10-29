package hu.qwaevisz.school.ejbservice.domain;

public class MarkDetailStub {

	private final String institute;

	private final int year;

	private final double averageGrade;

	public MarkDetailStub(String institute, int year, double averageGrade) {
		this.institute = institute;
		this.year = year;
		this.averageGrade = averageGrade;
	}

	public String getInstitute() {
		return this.institute;
	}

	public int getYear() {
		return this.year;
	}

	public double getAverageGrade() {
		return this.averageGrade;
	}

	@Override
	public String toString() {
		return "MarkDetailStub [institute=" + this.institute + ", year=" + this.year + ", averageGrade=" + this.averageGrade + "]";
	}

}
