package hu.qwaevisz.diskstore.ejbservice.facade;

import java.util.List;

import javax.ejb.Local;

import hu.qwaevisz.diskstore.ejbserviceclient.domain.DiskCategoryStub;
import hu.qwaevisz.diskstore.ejbserviceclient.domain.DiskStub;
import hu.qwaevisz.diskstore.ejbserviceclient.exception.ServiceException;

@Local
public interface DiskFacade {

	void insertDisk(final String reference, final String author, final String title, DiskCategoryStub category, final double price, final int numberOfSongs)
			throws ServiceException;

	DiskStub getDisk(Integer id) throws ServiceException;

	DiskStub getDisk(String reference) throws ServiceException;

	List<DiskStub> getDisks() throws ServiceException;

	DiskStub saveDisk(final String reference, final String author, final String title, DiskCategoryStub category, final double price, final int numberOfSongs)
			throws ServiceException;

	void removeDisk(final String reference) throws ServiceException;

}
