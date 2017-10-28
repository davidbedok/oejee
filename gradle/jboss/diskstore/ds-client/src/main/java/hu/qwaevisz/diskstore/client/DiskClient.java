package hu.qwaevisz.diskstore.client;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import hu.qwaevisz.diskstore.ejbserviceclient.DiskFacadeRemote;
import hu.qwaevisz.diskstore.ejbserviceclient.domain.DiskStub;
import hu.qwaevisz.diskstore.ejbserviceclient.exception.ServiceException;

public class DiskClient {

	private static final String JBOSS_INITIAL_CONTEXT_FACTORY = "org.jboss.naming.remote.client.InitialContextFactory";
	private static final String JBOSS_PROVIDER_URL = "remote://localhost:4447";
	private static final String JBOSS_URL_PKG_PREFIXES = "org.jboss.ejb.client.naming";

	private static final String JBOSS_NAMING_CLIENT_EJB_CONTEXT_KEY = "jboss.naming.client.ejb.context";
	private static final String JBOSS_NAMING_CLIENT_EJB_CONTEXT_VALUE = "true";

	private static final Logger LOGGER = Logger.getLogger(DiskClient.class);

	public static void main(final String[] args) throws Exception {
		System.out.println(new DiskClient().invoke("WAM124"));
	}

	private DiskStub invoke(final String reference) {
		DiskStub disk = null;
		try {
			final DiskFacadeRemote facade = this.lookup();
			disk = facade.getDisk(reference);
			LOGGER.info(disk);
		} catch (final ServiceException e) {
			e.printStackTrace();
		} catch (final NamingException e) {
			e.printStackTrace();
		}
		return disk;
	}

	private DiskFacadeRemote lookup() throws NamingException {
		final Hashtable<String, String> jndiProperties = new Hashtable<String, String>();
		jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, JBOSS_INITIAL_CONTEXT_FACTORY);
		jndiProperties.put(Context.PROVIDER_URL, JBOSS_PROVIDER_URL);
		jndiProperties.put(Context.URL_PKG_PREFIXES, JBOSS_URL_PKG_PREFIXES);
		jndiProperties.put(JBOSS_NAMING_CLIENT_EJB_CONTEXT_KEY, JBOSS_NAMING_CLIENT_EJB_CONTEXT_VALUE);
		final Context context = new InitialContext(jndiProperties);
		return (DiskFacadeRemote) context.lookup("diskstoreapp/dsservicemodule/DiskStoreService!hu.qwaevisz.diskstore.ejbserviceclient.DiskFacadeRemote");
	}

}