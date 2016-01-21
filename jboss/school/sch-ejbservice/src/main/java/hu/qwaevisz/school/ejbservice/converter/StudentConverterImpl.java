package hu.qwaevisz.school.ejbservice.converter;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import hu.qwaevisz.school.ejbservice.domain.InstituteStub;
import hu.qwaevisz.school.ejbservice.domain.StudentStub;
import hu.qwaevisz.school.persistence.entity.Mark;
import hu.qwaevisz.school.persistence.entity.Student;

@Stateless
public class StudentConverterImpl implements StudentConverter {

	@EJB
	private MarkConverter markConverter;

	@Override
	public StudentStub to(final Student student) {
		final InstituteStub institute = InstituteStub.valueOf(student.getInstitute().toString());
		final StudentStub studentStub = new StudentStub(student.getName(), student.getNeptun(), institute);
		for (final Mark mark : student.getMarks()) {
			studentStub.addMark(this.markConverter.to(mark));
		}
		return studentStub;
	}

	@Override
	public List<StudentStub> to(final List<Student> students) {
		final List<StudentStub> stubs = new ArrayList<>();
		for (final Student student : students) {
			stubs.add(this.to(student));
		}
		return stubs;
	}

}
