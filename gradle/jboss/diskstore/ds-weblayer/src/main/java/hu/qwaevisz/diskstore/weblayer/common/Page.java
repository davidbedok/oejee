package hu.qwaevisz.diskstore.weblayer.common;

public enum Page {

	LIST("list.jsp", "DiskList"),
	DISK_VIEW("disk.jsp", "Disk"),
	DISK_EDIT("disk-edit.jsp", "Disk");

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
