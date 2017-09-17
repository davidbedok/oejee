package hu.qwaevisz.inventory.webservice.common;

public enum Page {

	JAVA_SERVER_PAGES("list.jsp"),
	GROOVY_SERVER_PAGES("list.gsp"),
	GROOVY_SERVLET("list.groovy");

	private final String serverPage;

	public String getServerPage() {
		return this.serverPage;
	}

	private Page(final String serverPage) {
		this.serverPage = serverPage;
	}

}
