package hu.qwaevisz.diskstore.persistence.service;

import java.util.List;

import javax.ejb.Local;

import hu.qwaevisz.diskstore.persistence.entity.Disk;
import hu.qwaevisz.diskstore.persistence.entity.trunk.DiskCategory;
import hu.qwaevisz.diskstore.persistence.exception.PersistenceServiceException;

@Local
public interface DiskService {

	boolean exists(final String reference) throws PersistenceServiceException;

	void create(final String reference, final String author, final String title, DiskCategory category, final Double price, final Integer numberOfSongs)
			throws PersistenceServiceException;

	Disk readById(Integer id) throws PersistenceServiceException;

	Disk readByReference(String reference) throws PersistenceServiceException;

	List<Disk> readAll() throws PersistenceServiceException;

	void update(Integer id, final String reference, final String author, final String title, DiskCategory category, final Double price,
			final Integer numberOfSongs) throws PersistenceServiceException;

	void delete(Integer id) throws PersistenceServiceException;

}
