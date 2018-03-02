package hu.qwaevisz.webstore.webservice.exception;

import javax.xml.ws.WebFault;

import hu.qwaevisz.webstore.ejbservice.domain.ServiceError;

@WebFault(name = "WebStoreServiceFault", targetNamespace = "http://www.qwaevisz.hu/WebStore")
public class WebStoreServiceException extends Exception {

	private static final long serialVersionUID = 1L;

	private final ServiceError faultInfo;

	public WebStoreServiceException(String message, ServiceError faultInfo) {
		super(message);
		this.faultInfo = faultInfo;
	}

	public WebStoreServiceException(String message, ServiceError faultInfo, Throwable cause) {
		super(message, cause);
		this.faultInfo = faultInfo;
	}

	public ServiceError getFaultInfo() {
		return this.faultInfo;
	}

}
