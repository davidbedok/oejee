package hu.qwaevisz.webstore.webservice;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

import hu.qwaevisz.webstore.ejbservice.domain.Basket;
import hu.qwaevisz.webstore.ejbservice.domain.Product;
import hu.qwaevisz.webstore.ejbservice.exception.ServiceException;
import hu.qwaevisz.webstore.ejbservice.service.WebBasketService;
import hu.qwaevisz.webstore.webservice.exception.WebStoreServiceException;

@WebService(name = "WebStore", serviceName = "WebStoreService", targetNamespace = "http://www.qwaevisz.hu/WebStore")
@SOAPBinding(style = Style.DOCUMENT, use = Use.LITERAL, parameterStyle = ParameterStyle.WRAPPED)
public class WebStoreService {

	@EJB
	private WebBasketService service;

	@WebMethod(action = "http://www.qwaevisz.hu/WebStore/setIdentifier", operationName = "SetIdentifierRequest")
	@WebResult(name = "SetIdentifierResponse", partName = "setIdentifierPart")
	public void setIdentifier(@WebParam(name = "identifier") final String identifier) throws WebStoreServiceException {
		try {
			this.service.setIdentifier(identifier);
		} catch (final ServiceException e) {
			throw new WebStoreServiceException(e.getMessage(), e.getError());
		}
	}

	@WebMethod(action = "http://www.qwaevisz.hu/WebStore/getIdentifier", operationName = "GetIdentifierRequest")
	@WebResult(name = "GetIdentifierResponse", partName = "getIdentifierPart")
	public String getIdentifier() throws WebStoreServiceException {
		try {
			return this.service.getIdentifier();
		} catch (final ServiceException e) {
			throw new WebStoreServiceException(e.getMessage(), e.getError());
		}
	}

	@WebMethod(action = "http://www.qwaevisz.hu/WebStore/getBasketSize", operationName = "GetBasketSizeRequest")
	@WebResult(name = "GetBasketSizeResponse", partName = "getBasketSizePart")
	public int getBasketSize() throws WebStoreServiceException {
		try {
			return this.service.getBasketSize();
		} catch (final ServiceException e) {
			throw new WebStoreServiceException(e.getMessage(), e.getError());
		}
	}

	@WebMethod(action = "http://www.qwaevisz.hu/WebStore/addItem", operationName = "AddItemRequest")
	@WebResult(name = "AddItemResponse", partName = "addItemPart")
	public void addItem(@WebParam(name = "item") final Product product) throws WebStoreServiceException {
		try {
			this.service.addItem(product);
		} catch (final ServiceException e) {
			throw new WebStoreServiceException(e.getMessage(), e.getError());
		}
	}

	@WebMethod(action = "http://www.qwaevisz.hu/WebStore/getContent", operationName = "GetContentRequest")
	@WebResult(name = "GetContentResponse", partName = "getContentPart")
	public Basket getContent() throws WebStoreServiceException {
		try {
			return this.service.getContent();
		} catch (final ServiceException e) {
			throw new WebStoreServiceException(e.getMessage(), e.getError());
		}
	}

}
