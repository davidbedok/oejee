package hu.qwaevisz.magazine.ejbservice.facade;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import hu.qwaevisz.magazine.ejbservice.converter.MagazineConverter;
import hu.qwaevisz.magazine.ejbservice.domain.MagazineCategoryStub;
import hu.qwaevisz.magazine.ejbservice.domain.MagazineCriteria;
import hu.qwaevisz.magazine.ejbservice.domain.MagazineStub;
import hu.qwaevisz.magazine.ejbservice.exception.FacadeException;
import hu.qwaevisz.magazine.persistence.entity.Magazine;
import hu.qwaevisz.magazine.persistence.entity.trunk.MagazineCategory;
import hu.qwaevisz.magazine.persistence.exception.PersistenceServiceException;
import hu.qwaevisz.magazine.persistence.service.MagazineService;

@PermitAll
@Stateless(mappedName = "ejb/magazineFacade")
public class MagazineFacadeImpl implements MagazineFacade {

	private static final Logger LOGGER = Logger.getLogger(MagazineFacadeImpl.class);

	@EJB
	private MagazineService service;

	@EJB
	private MagazineConverter converter;

	@Override
	public MagazineStub getReference(final String reference) throws FacadeException {
		try {
			final MagazineStub stub = this.converter.to(this.service.read(reference));
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Get Magazine by reference (" + reference + ") --> " + stub);
			}
			return stub;
		} catch (final PersistenceServiceException e) {
			LOGGER.error(e, e);
			throw new FacadeException(e.getLocalizedMessage());
		}
	}

	@Override
	public List<MagazineStub> getReferences(final MagazineCriteria criteria) throws FacadeException {
		List<MagazineStub> stubs = new ArrayList<MagazineStub>();
		try {
			List<Magazine> magazines = null;
			if (criteria.getCategory() == null) {
				magazines = this.service.readAll();
			} else {
				magazines = this.service.read(MagazineCategory.valueOf(criteria.getCategory().name()));
			}
			stubs = this.converter.to(magazines);
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Get Magazines by criteria (" + criteria + ") --> " + stubs.size() + " magazine(s)");
			}
		} catch (final PersistenceServiceException e) {
			LOGGER.error(e, e);
			throw new FacadeException(e.getLocalizedMessage());
		}
		return stubs;
	}

	@Override
	public MagazineStub saveMagazine(final String reference, final String publisher, final String title, final int numberOfPages, final double price,
			final MagazineCategoryStub category) throws FacadeException {
		try {
			Magazine magazine = null;
			if (this.service.exists(reference)) {
				magazine = this.service.update(reference, publisher, title, numberOfPages, price, MagazineCategory.valueOf(category.name()));
			} else {
				magazine = this.service.create(reference, publisher, title, numberOfPages, price, MagazineCategory.valueOf(category.name()));
			}
			return this.converter.to(magazine);
		} catch (final PersistenceServiceException e) {
			LOGGER.error(e, e);
			throw new FacadeException(e.getLocalizedMessage());
		}
	}

	@Override
	@RolesAllowed("magadmin")
	public void removeMagazine(final String reference) throws FacadeException {
		try {
			this.service.delete(reference);
		} catch (final PersistenceServiceException e) {
			LOGGER.error(e, e);
			throw new FacadeException(e.getLocalizedMessage());
		}
	}

}
