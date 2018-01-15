package hu.qwaevisz.diskstore.client;

import hu.qwaevisz.diskstore.ejbserviceclient.DiskFacadeRemote;

public class JndiNameBuilder {

	private static final String APPLICATION_NAME = "diskstoreapp";
	private static final String MODULE_NAME = "dsservicemodule";

	public static String getServiceJndiName() {
		return APPLICATION_NAME + "/" + MODULE_NAME + "/" + DiskFacadeRemote.BEAN_NAME + "!" + DiskFacadeRemote.class.getName();
	}

}
