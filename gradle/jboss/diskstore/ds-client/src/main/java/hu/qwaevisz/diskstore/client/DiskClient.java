package hu.qwaevisz.diskstore.client;

import javax.naming.Context;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import hu.qwaevisz.diskstore.client.context.ContextFactory;
import hu.qwaevisz.diskstore.ejbserviceclient.DiskFacadeRemote;
import hu.qwaevisz.diskstore.ejbserviceclient.domain.DiskStub;
import hu.qwaevisz.diskstore.ejbserviceclient.exception.ServiceException;

public class DiskClient {

	private static final Logger LOGGER = Logger.getLogger(DiskClient.class);

	private ContextFactory contextFactory;

	public DiskClient(ContextFactory contextFactory) {
		this.contextFactory = contextFactory;
	}

	public DiskStub getDisk(final String reference) {
		DiskStub disk = null;
		try {
			final DiskFacadeRemote facade = this.getDiskService(this.contextFactory.getContext());
			disk = facade.getDisk(reference);
			LOGGER.info(disk);
		} catch (final ServiceException e) {
			LOGGER.error(e, e);
		} catch (final NamingException e) {
			LOGGER.error(e, e);
		}
		return disk;
	}

	private DiskFacadeRemote getDiskService(Context context) throws NamingException {
		return (DiskFacadeRemote) context.lookup(JndiNameBuilder.getServiceJndiName());
	}

}