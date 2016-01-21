package hu.qwaevisz.diskstore.ejbservice.facade;

import java.util.List;

import javax.ejb.Local;

import hu.qwaevisz.diskstore.ejbserviceclient.domain.DiskStub;
import hu.qwaevisz.diskstore.ejbserviceclient.exception.ServiceException;

@Local
public interface DiskFacade {

    DiskStub getDisk(int diskId) throws ServiceException;

    List<DiskStub> getAllDisks() throws ServiceException;

}
