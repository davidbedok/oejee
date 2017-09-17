package hu.qwaevisz.magazine.ejbservice.facade;

import java.util.List;

import javax.ejb.Local;

import hu.qwaevisz.magazine.ejbservice.domain.MagazineCategoryStub;
import hu.qwaevisz.magazine.ejbservice.domain.MagazineCriteria;
import hu.qwaevisz.magazine.ejbservice.domain.MagazineStub;
import hu.qwaevisz.magazine.ejbservice.exception.FacadeException;

@Local
public interface MagazineFacade {

	MagazineStub getReference(String reference) throws FacadeException;

	List<MagazineStub> getReferences(MagazineCriteria criteria) throws FacadeException;

	MagazineStub saveMagazine(String reference, String publisher, String title, int numberOfPages, double price, MagazineCategoryStub category)
			throws FacadeException;

	void removeMagazine(String reference) throws FacadeException;

}
