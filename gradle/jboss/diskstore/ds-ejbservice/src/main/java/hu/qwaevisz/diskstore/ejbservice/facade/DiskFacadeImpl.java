package hu.qwaevisz.diskstore.ejbservice.facade;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import hu.qwaevisz.diskstore.ejbservice.converter.DiskConverter;
import hu.qwaevisz.diskstore.ejbserviceclient.DiskFacadeRemote;
import hu.qwaevisz.diskstore.ejbserviceclient.domain.DiskCategoryStub;
import hu.qwaevisz.diskstore.ejbserviceclient.domain.DiskStub;
import hu.qwaevisz.diskstore.ejbserviceclient.exception.ServiceException;
import hu.qwaevisz.diskstore.persistence.entity.Disk;
import hu.qwaevisz.diskstore.persistence.entity.trunk.DiskCategory;
import hu.qwaevisz.diskstore.persistence.exception.PersistenceServiceException;
import hu.qwaevisz.diskstore.persistence.service.DiskService;

@Stateless(mappedName = "ejb/diskFacade", name = DiskFacadeRemote.BEAN_NAME)
public class DiskFacadeImpl implements DiskFacade, DiskFacadeRemote {

	private static final Logger LOGGER = Logger.getLogger(DiskFacadeImpl.class);

	@EJB
	private DiskService service;

	@EJB
	private DiskConverter converter;

	@Override
	public void insertDisk(final String reference, final String author, final String title, final DiskCategoryStub category, final double price,
			final int numberOfSongs) throws ServiceException {
		try {
			final DiskCategory diskCategory = DiskCategory.valueOf(category.name());
			this.service.create(reference, author, title, diskCategory, price, numberOfSongs);
		} catch (final PersistenceServiceException e) {
			LOGGER.error(e, e);
			throw new ServiceException("Failed to insert Disk (reference: " + reference + ", author: " + author + ", title: " + title + ", price: " + price
					+ ", numberOfSongs: " + numberOfSongs + ")");
		}
	}

	@Override
	public DiskStub getDisk(final Integer id) throws ServiceException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Get Disk by id (" + id + ")");
		}
		try {
			return this.converter.to(this.service.readById(id));
		} catch (final PersistenceServiceException e) {
			LOGGER.error(e, e);
			throw new ServiceException("Failed to get Disk by id (" + id + ")");
		}
	}

	@Override
	public DiskStub getDisk(final String reference) throws ServiceException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Get Disk by reference (" + reference + ")");
		}
		try {
			return this.converter.to(this.service.readByReference(reference));
		} catch (final PersistenceServiceException e) {
			LOGGER.error(e, e);
			throw new ServiceException("Failed to get Disk by reference (" + reference + ")");
		}
	}

	@Override
	public List<DiskStub> getDisks() throws ServiceException {
		try {
			return this.converter.to(this.service.readAll());
		} catch (final PersistenceServiceException e) {
			LOGGER.error(e, e);
			throw new ServiceException("Failed to get all Disks");
		}
	}

	@Override
	public DiskStub saveDisk(final String reference, final String author, final String title, final DiskCategoryStub category, final double price,
			final int numberOfSongs) throws ServiceException {
		try {
			final DiskCategory diskCategory = DiskCategory.valueOf(category.name());
			if (this.service.exists(reference)) {
				final Disk disk = this.service.readByReference(reference);
				this.service.update(disk.getId(), reference, author, title, diskCategory, price, numberOfSongs);
			} else {
				this.service.create(reference, author, title, diskCategory, price, numberOfSongs);
			}
			return this.converter.to(this.service.readByReference(reference));
		} catch (final PersistenceServiceException e) {
			LOGGER.error(e, e);
			throw new ServiceException("Failed to change Disks (reference: " + reference + ", author: " + author + ", title: " + title + ", price: " + price
					+ ", numberOfSongs: " + numberOfSongs + ")");
		}
	}

	@Override
	public void removeDisk(final String reference) throws ServiceException {
		try {
			final Disk disk = this.service.readByReference(reference);
			if (disk != null) {
				this.service.delete(disk.getId());
			} else {
				LOGGER.warn("Disk is not exist by reference (" + reference + ").");
			}
		} catch (final PersistenceServiceException e) {
			LOGGER.error(e, e);
			throw new ServiceException("Failed to remove Disk by reference (" + reference + ")");
		}
	}

}
