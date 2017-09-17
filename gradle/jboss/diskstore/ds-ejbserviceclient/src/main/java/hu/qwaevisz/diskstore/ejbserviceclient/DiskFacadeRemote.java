package hu.qwaevisz.diskstore.ejbserviceclient;

import javax.ejb.Remote;

import hu.qwaevisz.diskstore.ejbserviceclient.domain.DiskStub;
import hu.qwaevisz.diskstore.ejbserviceclient.exception.ServiceException;

@Remote
public interface DiskFacadeRemote {

	DiskStub getDisk(String reference) throws ServiceException;

}
