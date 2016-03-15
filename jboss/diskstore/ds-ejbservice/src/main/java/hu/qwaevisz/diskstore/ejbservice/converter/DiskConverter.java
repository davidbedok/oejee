package hu.qwaevisz.diskstore.ejbservice.converter;

import java.util.List;

import javax.ejb.Local;

import hu.qwaevisz.diskstore.ejbserviceclient.domain.DiskStub;
import hu.qwaevisz.diskstore.persistence.entity.Disk;

@Local
public interface DiskConverter {

	DiskStub to(Disk disk);

	List<DiskStub> to(List<Disk> disks);

}
