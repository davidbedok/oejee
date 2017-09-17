package hu.qwaevisz.magazine.weblayer.common;

public enum Page {

	LOGIN("login.jsp", "Login"),
	ERROR("error.jsp", "Error"),
	LIST("list.jsp", "MagazineList"),
	MAGAZINE_VIEW("magazine.jsp", "Magazine"),
	MAGAZINE_EDIT("magazine-edit.jsp", "Magazine");

	private final String jspName;
	private final String url;

	public String getJspName() {
		return this.jspName;
	}

	public String getUrl() {
		return this.url;
	}

	private Page(final String jspName, final String url) {
		this.jspName = jspName;
		this.url = url;
	}

}
