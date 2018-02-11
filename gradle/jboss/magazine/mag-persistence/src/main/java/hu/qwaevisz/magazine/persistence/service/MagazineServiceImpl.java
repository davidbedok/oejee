package hu.qwaevisz.magazine.persistence.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import hu.qwaevisz.magazine.persistence.entity.Magazine;
import hu.qwaevisz.magazine.persistence.entity.trunk.MagazineCategory;
import hu.qwaevisz.magazine.persistence.exception.PersistenceServiceException;

@Stateless(mappedName = "ejb/magazineService")
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class MagazineServiceImpl implements MagazineService {

	private static final Logger LOGGER = Logger.getLogger(MagazineServiceImpl.class);

	@PersistenceContext(unitName = "mag-persistence-unit")
	private EntityManager entityManager;

	@Override
	public boolean exists(String reference) throws PersistenceServiceException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Check Magazine by reference (" + reference + ")");
		}
		try {
			return this.entityManager.createNamedQuery(Magazine.COUNT_BY_REFERENCE, Long.class).setParameter("reference", reference).getSingleResult() == 1;
		} catch (final Exception e) {
			throw new PersistenceServiceException("Unknown error during counting Magazines by reference (" + reference + ")! " + e.getLocalizedMessage(), e);
		}
	}

	@Override
	public Magazine create(String reference, String publisher, String title, int numberOfPages, double price, MagazineCategory category)
			throws PersistenceServiceException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Create Magazine (reference: " + reference + ", publisher: " + publisher + ", title: " + title + ", numberOfPages: " + numberOfPages
					+ ", price: " + price + ", category: " + category + ")");
		}
		try {
			final Magazine magazine = new Magazine(reference, publisher, title, numberOfPages, price, category);
			this.entityManager.persist(magazine);
			return magazine;
		} catch (final Exception e) {
			throw new PersistenceServiceException("Unknown error during persisting Magazine (" + reference + ")! " + e.getLocalizedMessage(), e);
		}
	}

	@Override
	public Magazine read(Long id) throws PersistenceServiceException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Get Magazine by id (" + id + ")");
		}
		Magazine result = null;
		try {
			result = this.entityManager.createNamedQuery(Magazine.GET_BY_ID, Magazine.class).setParameter("id", id).getSingleResult();
		} catch (final Exception e) {
			throw new PersistenceServiceException("Unknown error when fetching Magazine by id (" + id + ")! " + e.getLocalizedMessage(), e);
		}
		return result;
	}

	@Override
	public Magazine read(String reference) throws PersistenceServiceException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Get Magazine by reference (" + reference + ")");
		}
		Magazine result = null;
		try {
			result = this.entityManager.createNamedQuery(Magazine.GET_BY_REFERENCE, Magazine.class).setParameter("reference", reference).getSingleResult();
		} catch (final Exception e) {
			throw new PersistenceServiceException("Unknown error when fetching Magazine by reference (" + reference + ")! " + e.getLocalizedMessage(), e);
		}
		return result;
	}

	@Override
	public List<Magazine> readAll() throws PersistenceServiceException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Get Magazines");
		}
		List<Magazine> result = null;
		try {
			result = this.entityManager.createNamedQuery(Magazine.GET_ALL, Magazine.class).getResultList();
		} catch (final Exception e) {
			throw new PersistenceServiceException("Unknown error when fetching Magazines! " + e.getLocalizedMessage(), e);
		}
		return result;
	}

	@Override
	public List<Magazine> read(MagazineCategory category) throws PersistenceServiceException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Get Magazines by Category");
		}
		List<Magazine> result = null;
		try {
			result = this.entityManager.createNamedQuery(Magazine.GET_ALL_BY_CATEGORY, Magazine.class).setParameter("category", category).getResultList();
		} catch (final Exception e) {
			throw new PersistenceServiceException("Unknown error when fetching Magazines! " + e.getLocalizedMessage(), e);
		}
		return result;
	}

	@Override
	public Magazine update(String reference, String publisher, String title, int numberOfPages, double price, MagazineCategory category)
			throws PersistenceServiceException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Update Magazine (reference: " + reference + ", publisher: " + publisher + ", title: " + title + ", numberOfPages: " + numberOfPages
					+ ", price: " + price + ", category: " + category + ")");
		}
		try {
			final Magazine magazine = this.read(reference);
			magazine.setPublisher(publisher);
			magazine.setTitle(title);
			magazine.setNumberOfPages(numberOfPages);
			magazine.setPrice(price);
			magazine.setCategory(category);
			return this.entityManager.merge(magazine);
		} catch (final Exception e) {
			throw new PersistenceServiceException("Unknown error when merging Magazine! " + e.getLocalizedMessage(), e);
		}
	}

	@Override
	public void delete(String reference) throws PersistenceServiceException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Remove Magazine by reference (" + reference + ")");
		}
		try {
			this.entityManager.createNamedQuery(Magazine.REMOVE_BY_REFERENCE).setParameter("reference", reference).executeUpdate();
		} catch (final Exception e) {
			throw new PersistenceServiceException("Unknown error when removing Magazine by reference (" + reference + ")! " + e.getLocalizedMessage(), e);
		}
	}

}
