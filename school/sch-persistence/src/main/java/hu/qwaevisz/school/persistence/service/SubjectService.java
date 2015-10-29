package hu.qwaevisz.school.persistence.service;

import javax.ejb.Local;

import hu.qwaevisz.school.persistence.entity.Subject;
import hu.qwaevisz.school.persistence.exception.PersistenceServiceException;

@Local
public interface SubjectService {

	Subject read(Long id) throws PersistenceServiceException;

	Subject read(String name) throws PersistenceServiceException;

}
