package hu.qwaevisz.diskstore.client.context;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class JndiPropertiesContextFactory implements ContextFactory {

	@Override
	public Context getContext() throws NamingException {
		return new InitialContext();
	}

}
