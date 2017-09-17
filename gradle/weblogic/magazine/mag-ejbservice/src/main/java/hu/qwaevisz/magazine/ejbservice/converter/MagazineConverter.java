package hu.qwaevisz.magazine.ejbservice.converter;

import java.util.List;

import javax.ejb.Local;

import hu.qwaevisz.magazine.ejbservice.domain.MagazineStub;
import hu.qwaevisz.magazine.persistence.entity.Magazine;

@Local
public interface MagazineConverter {

	MagazineStub to(Magazine book);

	List<MagazineStub> to(List<Magazine> books);

}
