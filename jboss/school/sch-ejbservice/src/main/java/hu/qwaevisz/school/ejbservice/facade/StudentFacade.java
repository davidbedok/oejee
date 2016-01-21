package hu.qwaevisz.school.ejbservice.facade;

import java.util.List;

import javax.ejb.Local;

import hu.qwaevisz.school.ejbservice.domain.StudentStub;
import hu.qwaevisz.school.ejbservice.exception.AdaptorException;

@Local
public interface StudentFacade {

	StudentStub getStudent(String neptun) throws AdaptorException;

	List<StudentStub> getAllStudents() throws AdaptorException;

	void removeStudent(String neptun) throws AdaptorException;

}
