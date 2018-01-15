package hu.qwaevisz.diskstore.client.main;

import hu.qwaevisz.diskstore.client.DiskClient;
import hu.qwaevisz.diskstore.client.context.ContextFactory;
import hu.qwaevisz.diskstore.client.context.JndiPropertiesContextFactory;
import hu.qwaevisz.diskstore.client.context.ProgrammedContextFactory;
import hu.qwaevisz.diskstore.ejbserviceclient.domain.DiskStub;

public class Application {

	public static void main(final String[] args) throws Exception {
		printDisk(new JndiPropertiesContextFactory());
		printDisk(new ProgrammedContextFactory());
	}

	private static void printDisk(ContextFactory contextFactory) {
		System.out.println(getDisk("WAM124", contextFactory));
	}

	private static DiskStub getDisk(String reference, ContextFactory contextFactory) {
		return new DiskClient(contextFactory).getDisk(reference);
	}

}
