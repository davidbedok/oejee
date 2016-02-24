package hu.qwaevisz.magazine.ejbservice.converter;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

import hu.qwaevisz.magazine.ejbservice.domain.MagazineCategoryStub;
import hu.qwaevisz.magazine.ejbservice.domain.MagazineStub;
import hu.qwaevisz.magazine.persistence.entity.Magazine;

@PermitAll
@Stateless
public class MagazineConverterImpl implements MagazineConverter {

	@Override
	public MagazineStub to(final Magazine magazine) {
		final MagazineCategoryStub category = MagazineCategoryStub.valueOf(magazine.getCategory().toString());
		return new MagazineStub(magazine.getReference(), magazine.getPublisher(), magazine.getTitle(), category, magazine.getPrice(),
				magazine.getNumberOfPages());
	}

	@Override
	public List<MagazineStub> to(final List<Magazine> magazines) {
		final List<MagazineStub> result = new ArrayList<>();
		for (final Magazine book : magazines) {
			result.add(this.to(book));
		}
		return result;
	}

}
