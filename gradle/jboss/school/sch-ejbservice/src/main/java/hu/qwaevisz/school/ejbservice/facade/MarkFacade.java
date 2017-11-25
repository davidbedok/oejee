package hu.qwaevisz.school.ejbservice.facade;

import java.util.List;

import javax.ejb.Local;

import hu.qwaevisz.school.ejbservice.domain.MarkDetailStub;
import hu.qwaevisz.school.ejbservice.domain.MarkStub;
import hu.qwaevisz.school.ejbservice.exception.AdaptorException;

@Local
public interface MarkFacade {

	List<MarkDetailStub> getMarkDetails(String subject) throws AdaptorException;

	MarkStub addMark(String subject, String neptun, int grade, String note) throws AdaptorException;

	List<MarkStub> getMarks(String studentNeptun, String subjectNameTerm, int minimumGrade, int maximumGrade) throws AdaptorException;

}
