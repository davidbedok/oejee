package hu.qwaevisz.diskstore.ejbservice.converter;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import hu.qwaevisz.diskstore.ejbserviceclient.domain.DiskCategoryStub;
import hu.qwaevisz.diskstore.ejbserviceclient.domain.DiskStub;
import hu.qwaevisz.diskstore.persistence.entity.Disk;

@Stateless
public class DiskConverterImpl implements DiskConverter {

	@Override
	public DiskStub to(final Disk disk) {
		final DiskCategoryStub diskCategoryStub = DiskCategoryStub.valueOf(disk.getCategory().name());
		return new DiskStub(disk.getReference(), disk.getAuthor(), disk.getTitle(), diskCategoryStub, disk.getPrice(), disk.getNumberOfSongs());
	}

	@Override
	public List<DiskStub> to(final List<Disk> disks) {
		final List<DiskStub> result = new ArrayList<>();
		for (final Disk disk : disks) {
			result.add(this.to(disk));
		}
		return result;
	}

}
