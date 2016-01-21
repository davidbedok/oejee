package hu.qwaevisz.school.ejbservice.converter;

import java.util.List;

import javax.ejb.Local;

import hu.qwaevisz.school.ejbservice.domain.StudentStub;
import hu.qwaevisz.school.persistence.entity.Student;

@Local
public interface StudentConverter {

	StudentStub to(Student student);

	List<StudentStub> to(List<Student> students);

}
