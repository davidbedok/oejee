package hu.qwaevisz.school.persistence.result;

import hu.qwaevisz.school.persistence.entity.trunk.Institute;

public class MarkDetailResult {

	private final Institute institute;

	private final Integer year;

	private final double averageGrade;

	public MarkDetailResult(Institute institute, Integer year, double averageGrade) {
		this.institute = institute;
		this.year = year;
		this.averageGrade = averageGrade;
	}

	public Institute getInstitute() {
		return this.institute;
	}

	public Integer getYear() {
		return this.year;
	}

	public double getAverageGrade() {
		return this.averageGrade;
	}

	@Override
	public String toString() {
		return "MarkDetailResult [institute=" + this.institute + ", year=" + this.year + ", averageGrade=" + this.averageGrade + "]";
	}

}
