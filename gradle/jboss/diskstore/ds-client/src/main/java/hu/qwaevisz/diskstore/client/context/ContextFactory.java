package hu.qwaevisz.diskstore.client.context;

import javax.naming.Context;
import javax.naming.NamingException;

public interface ContextFactory {

	Context getContext() throws NamingException;

}
