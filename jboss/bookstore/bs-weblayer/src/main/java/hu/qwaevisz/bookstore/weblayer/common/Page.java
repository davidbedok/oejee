package hu.qwaevisz.bookstore.weblayer.common;

public enum Page {

	LIST("list.jsp", "BookList"),
	BOOK_VIEW("book.jsp", "Book"),
	BOOK_EDIT("book-edit.jsp", "Book");

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
