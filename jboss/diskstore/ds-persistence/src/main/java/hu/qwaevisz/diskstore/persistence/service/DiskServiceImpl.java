package hu.qwaevisz.diskstore.persistence.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.mybatis.cdi.Mapper;

import hu.qwaevisz.diskstore.persistence.entity.Disk;
import hu.qwaevisz.diskstore.persistence.entity.trunk.DiskCategory;
import hu.qwaevisz.diskstore.persistence.exception.PersistenceServiceException;
import hu.qwaevisz.diskstore.persistence.mapper.DiskMapper;

@Stateless(mappedName = "ejb/diskService")
public class DiskServiceImpl implements DiskService {

	private static final Logger LOGGER = Logger.getLogger(DiskServiceImpl.class);

	@Inject
	@Mapper
	private DiskMapper mapper;

	@Inject
	private SqlSession sqlSession;

	@Override
	public boolean exists(final String reference) throws PersistenceServiceException {
		try {
			return this.mapper.count(reference) == 1;
		} catch (final Exception e) {
			LOGGER.error(e, e);
			throw new PersistenceServiceException("Failed to check Disk by reference (" + reference + ")! " + e.getMessage(), e);
		}
	}

	@Override
	public void create(final String reference, final String author, final String title, final DiskCategory category, final Double price,
			final Integer numberOfSongs) throws PersistenceServiceException {
		LOGGER.debug("Create new Disk (reference: " + reference + ", author: " + author + ", title: " + title + ", category: " + category + ", price: " + price
				+ ", numberOfSongs: " + numberOfSongs + ")");
		try {
			this.mapper.create(new Disk(reference, author, title, category, price, numberOfSongs));
		} catch (final Exception e) {
			LOGGER.error(e, e);
			throw new PersistenceServiceException("Failed to create new Disk (reference: " + reference + ", author: " + author + ", title: " + title
					+ ", category: " + category + ", price: " + price + ", numberOfSongs: " + numberOfSongs + ")! " + e.getMessage(), e);
		}
	}

	@Override
	public Disk readById(final Integer id) throws PersistenceServiceException {
		LOGGER.debug("Read Disk by id (" + id + ")");
		try {
			return this.mapper.readById(id);
		} catch (final Exception e) {
			LOGGER.error(e, e);
			throw new PersistenceServiceException("Failed to read Disk by id (" + id + ")! " + e.getMessage(), e);
		}
	}

	@Override
	public Disk readByReference(final String reference) throws PersistenceServiceException {
		LOGGER.debug("Read Disk by reference (" + reference + ")");
		try {
			return this.mapper.readByReference(reference);
		} catch (final Exception e) {
			LOGGER.error(e, e);
			throw new PersistenceServiceException("Failed to read Disk by reference (" + reference + ")! " + e.getMessage(), e);
		}
	}

	@Override
	public List<Disk> readAll() throws PersistenceServiceException {
		LOGGER.debug("Read all Disks");
		try {
			return this.mapper.readAll();
		} catch (final Exception e) {
			LOGGER.error(e, e);
			throw new PersistenceServiceException("Failed to read all Disks! " + e.getMessage(), e);
		}
	}

	@Override
	public void update(final Integer id, final String reference, final String author, final String title, final DiskCategory category, final Double price,
			final Integer numberOfSongs) throws PersistenceServiceException {
		LOGGER.debug("Update Disk (id: " + id + ", reference: " + reference + ", author: " + author + ", title: " + title + ", category: " + category
				+ ", price: " + price + ", numberOfSongs: " + numberOfSongs + ")");
		try {
			final Disk disk = this.mapper.readById(id);
			disk.setAuthor(author);
			disk.setTitle(title);
			disk.setCategory(category);
			disk.setPrice(price);
			disk.setNumberOfSongs(numberOfSongs);
			this.mapper.update(disk);
		} catch (final Exception e) {
			LOGGER.error(e, e);
			throw new PersistenceServiceException("Failed to update Disk (reference: " + reference + ", author: " + author + ", title: " + title
					+ ", category: " + category + ", price: " + price + ", numberOfSongs: " + numberOfSongs + ")! " + e.getMessage(), e);
		}
	}

	@Override
	public void delete(final Integer id) throws PersistenceServiceException {
		LOGGER.debug("Delete Disk by id (" + id + ")");
		try {
			this.mapper.delete(id);
		} catch (final Exception e) {
			LOGGER.error(e, e);
			throw new PersistenceServiceException("Failed to delete Disk (" + id + ")! " + e.getMessage(), e);
		}
	}
}
