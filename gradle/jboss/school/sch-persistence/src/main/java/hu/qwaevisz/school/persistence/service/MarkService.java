package hu.qwaevisz.school.persistence.service;

import java.util.List;

import javax.ejb.Local;

import hu.qwaevisz.school.persistence.entity.Mark;
import hu.qwaevisz.school.persistence.exception.PersistenceServiceException;
import hu.qwaevisz.school.persistence.result.MarkDetailResult;

@Local
public interface MarkService {

	int count(String studentNeptun) throws PersistenceServiceException;

	Mark create(Long studentId, Long subjectId, Integer grade, String note) throws PersistenceServiceException;

	List<MarkDetailResult> read(String subjectName) throws PersistenceServiceException;

	List<Mark> read(String studentNeptun, String subjectNameTerm, Integer minimumGrade, Integer maximumGrade) throws PersistenceServiceException;

}
