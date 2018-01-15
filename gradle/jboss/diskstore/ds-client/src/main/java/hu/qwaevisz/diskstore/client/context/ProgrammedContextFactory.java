package hu.qwaevisz.diskstore.client.context;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ProgrammedContextFactory implements ContextFactory {

	private static final String JBOSS_NAMING_CLIENT_EJB_CONTEXT_KEY = "jboss.naming.client.ejb.context";

	@Override
	public Context getContext() throws NamingException {
		final Hashtable<String, String> jndiProperties = new Hashtable<String, String>();
		jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
		jndiProperties.put(Context.PROVIDER_URL, "remote://localhost:4447");
		jndiProperties.put(JBOSS_NAMING_CLIENT_EJB_CONTEXT_KEY, "true");
		return new InitialContext(jndiProperties);
	}

}
