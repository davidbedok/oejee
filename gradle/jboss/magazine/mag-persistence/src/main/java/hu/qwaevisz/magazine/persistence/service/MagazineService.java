package hu.qwaevisz.magazine.persistence.service;

import java.util.List;

import javax.ejb.Local;

import hu.qwaevisz.magazine.persistence.entity.Magazine;
import hu.qwaevisz.magazine.persistence.entity.trunk.MagazineCategory;
import hu.qwaevisz.magazine.persistence.exception.PersistenceServiceException;

@Local
public interface MagazineService {

	boolean exists(String reference) throws PersistenceServiceException;

	Magazine create(String reference, String publisher, String title, int numberOfPages, double price, MagazineCategory category)
			throws PersistenceServiceException;

	Magazine read(Long id) throws PersistenceServiceException;

	Magazine read(String reference) throws PersistenceServiceException;

	List<Magazine> readAll() throws PersistenceServiceException;

	List<Magazine> read(MagazineCategory category) throws PersistenceServiceException;

	Magazine update(String reference, String publisher, String title, int numberOfPages, double price, MagazineCategory category)
			throws PersistenceServiceException;

	void delete(String reference) throws PersistenceServiceException;

}
