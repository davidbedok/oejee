package hu.qwaevisz.school.ejbservice.converter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;

import hu.qwaevisz.school.ejbservice.domain.Grade;
import hu.qwaevisz.school.ejbservice.domain.MarkDetailStub;
import hu.qwaevisz.school.ejbservice.domain.MarkStub;
import hu.qwaevisz.school.ejbservice.domain.SubjectStub;
import hu.qwaevisz.school.ejbservice.domain.TeacherStub;
import hu.qwaevisz.school.persistence.entity.Mark;
import hu.qwaevisz.school.persistence.entity.Subject;
import hu.qwaevisz.school.persistence.entity.Teacher;
import hu.qwaevisz.school.persistence.result.MarkDetailResult;

@Stateless
public class MarkConverterImpl implements MarkConverter {

	@Override
	public MarkStub to(Mark mark) {
		MarkStub stub = null;
		if (mark != null) {
			stub = new MarkStub(this.to(mark.getSubject()), Grade.fromValue(mark.getGrade()), mark.getNote(), mark.getDate());
		}
		return stub;
	}

	private SubjectStub to(Subject subject) {
		return new SubjectStub(subject.getName(), this.to(subject.getTeacher()), subject.getDescription());
	}

	private TeacherStub to(Teacher teacher) {
		return new TeacherStub(teacher.getName(), teacher.getNeptun());
	}

	@Override
	public List<MarkDetailStub> to(List<MarkDetailResult> results) {
		final List<MarkDetailStub> stubs = new ArrayList<>();
		for (final MarkDetailResult result : results) {
			stubs.add(this.to(result));
		}
		return stubs;
	}

	private MarkDetailStub to(final MarkDetailResult result) {
		return new MarkDetailStub(result.getInstitute().toString(), this.getYearFromDate(result.getYear()), result.getAverageGrade());
	}

	private int getYearFromDate(Date date) {
		final Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.YEAR);
	}

}
