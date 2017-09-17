package hu.qwaevisz.shopping.report.exception;

public class ReportException extends Exception {

	private static final long serialVersionUID = -155816495158449560L;

	public ReportException(final String message) {
		super(message);
	}

	public ReportException(final String message, final Throwable cause) {
		super(message, cause);
	}

}
