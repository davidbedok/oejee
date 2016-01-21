package hu.qwaevisz.diskstore.ejbservice.facade;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import hu.qwaevisz.diskstore.ejbserviceclient.DiskFacadeRemote;
import hu.qwaevisz.diskstore.ejbserviceclient.domain.DiskStub;
import hu.qwaevisz.diskstore.ejbserviceclient.exception.ServiceException;

@Stateless(mappedName = "ejb/diskFacade")
public class DiskFacadeImpl implements DiskFacade, DiskFacadeRemote {

    private static final Logger LOGGER = Logger.getLogger(DiskFacadeImpl.class);

    @Override
    public DiskStub getDisk(int diskId) throws ServiceException {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Get Disk by id (" + diskId + ")");
        }
        return new DiskStub(diskId, "Hans Zimmer", "Gladiator", 4500);
    }

    @Override
    public List<DiskStub> getAllDisks() throws ServiceException {
        final List<DiskStub> stubs = new ArrayList<>();
        stubs.add(new DiskStub(1001, "Hans Zimmer", "Gladiator", 4500));
        stubs.add(new DiskStub(1011, "Ennio Morricone", "The Good, the Bad and the Ugly", 5600));
        return stubs;
    }

}
