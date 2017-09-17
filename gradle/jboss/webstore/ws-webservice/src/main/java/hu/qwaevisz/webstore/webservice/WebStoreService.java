package hu.qwaevisz.webstore.webservice;

import java.util.List;

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
import hu.qwaevisz.webstore.ejbservice.domain.BrandStub;
import hu.qwaevisz.webstore.ejbservice.domain.ProductStub;
import hu.qwaevisz.webstore.ejbservice.exception.ServiceException;
import hu.qwaevisz.webstore.ejbservice.service.StoreService;
import hu.qwaevisz.webstore.ejbservice.service.WebBasketService;
import hu.qwaevisz.webstore.webservice.exception.WebStoreServiceException;

@WebService(name = "WebStore", serviceName = "WebStoreService", targetNamespace = "http://www.qwaevisz.hu/WebStore")
@SOAPBinding(style = Style.DOCUMENT, use = Use.LITERAL, parameterStyle = ParameterStyle.WRAPPED)
public class WebStoreService {

	@EJB
	private WebBasketService basketService;

	@EJB
	private StoreService storeService;

	@WebMethod(action = "http://www.qwaevisz.hu/WebStore/setBasketIdentifier", operationName = "SetBasketIdentifier")
	public void setBasketIdentifier(@WebParam(name = "Identifier") final String identifier) throws WebStoreServiceException {
		try {
			this.basketService.setIdentifier(identifier);
		} catch (final ServiceException e) {
			throw new WebStoreServiceException(e.getMessage(), e.getError());
		}
	}

	@WebMethod(action = "http://www.qwaevisz.hu/WebStore/getBasketIdentifier", operationName = "GetBasketIdentifier")
	@WebResult(name = "Identifier")
	public String getBasketIdentifier() throws WebStoreServiceException {
		try {
			return this.basketService.getIdentifier();
		} catch (final ServiceException e) {
			throw new WebStoreServiceException(e.getMessage(), e.getError());
		}
	}

	@WebMethod(action = "http://www.qwaevisz.hu/WebStore/getBasketSize", operationName = "GetBasketSize")
	@WebResult(name = "BasketSize")
	public int getBasketSize() throws WebStoreServiceException {
		try {
			return this.basketService.getBasketSize();
		} catch (final ServiceException e) {
			throw new WebStoreServiceException(e.getMessage(), e.getError());
		}
	}

	@WebMethod(action = "http://www.qwaevisz.hu/WebStore/addItemToBasket", operationName = "AddItemToBasket")
	public void addItemToBasket(@WebParam(name = "ProductName") final String productName) throws WebStoreServiceException {
		try {
			this.basketService.addItem(productName);
		} catch (final ServiceException e) {
			throw new WebStoreServiceException(e.getMessage(), e.getError());
		}
	}

	@WebMethod(action = "http://www.qwaevisz.hu/WebStore/removeItemFromBasket", operationName = "RemoveItemFromBasket")
	public void removeItemFromBasket(@WebParam(name = "ProductName") final String productName) throws WebStoreServiceException {
		try {
			this.basketService.removeItem(productName);
		} catch (final ServiceException e) {
			throw new WebStoreServiceException(e.getMessage(), e.getError());
		}
	}

	@WebMethod(action = "http://www.qwaevisz.hu/WebStore/getBasketContent", operationName = "GetBasketContent")
	@WebResult(name = "Basket")
	public Basket getBasketContent() throws WebStoreServiceException {
		try {
			return this.basketService.getContent();
		} catch (final ServiceException e) {
			throw new WebStoreServiceException(e.getMessage(), e.getError());
		}
	}

	@WebMethod(action = "http://www.qwaevisz.hu/WebStore/addProduct", operationName = "AddProduct")
	public void addProduct(@WebParam(name = "Product") final ProductStub product) throws WebStoreServiceException {
		try {
			this.storeService.add(product);
		} catch (final ServiceException e) {
			throw new WebStoreServiceException(e.getMessage(), e.getError());
		}
	}

	@WebMethod(action = "http://www.qwaevisz.hu/WebStore/getProduct", operationName = "GetProduct")
	@WebResult(name = "Product")
	public ProductStub getProduct(@WebParam(name = "Name") String name) throws WebStoreServiceException {
		try {
			return this.storeService.get(name);
		} catch (ServiceException e) {
			throw new WebStoreServiceException(e.getMessage(), e.getError());
		}
	}

	@WebMethod(action = "http://www.qwaevisz.hu/WebStore/listProductsByNameTerm", operationName = "ListProductsByNameTerm")
	@WebResult(name = "Product")
	public List<ProductStub> listProductsByNameTerm(@WebParam(name = "NameTerm") String nameTerm) throws WebStoreServiceException {
		try {
			return this.storeService.list(nameTerm);
		} catch (ServiceException e) {
			throw new WebStoreServiceException(e.getMessage(), e.getError());
		}
	}

	@WebMethod(action = "http://www.qwaevisz.hu/WebStore/listProductsByBrand", operationName = "ListProductsByBrand")
	@WebResult(name = "Product")
	public List<ProductStub> listProductsByBrand(@WebParam(name = "Brand") BrandStub brand) throws WebStoreServiceException {
		try {
			return this.storeService.list(brand);
		} catch (ServiceException e) {
			throw new WebStoreServiceException(e.getMessage(), e.getError());
		}
	}

	@WebMethod(action = "http://www.qwaevisz.hu/WebStore/listAllProducts", operationName = "ListAllProducts")
	@WebResult(name = "Product")
	public List<ProductStub> listAllProducts() throws WebStoreServiceException {
		try {
			return this.storeService.getAll();
		} catch (ServiceException e) {
			throw new WebStoreServiceException(e.getMessage(), e.getError());
		}
	}

	@WebMethod(action = "http://www.qwaevisz.hu/WebStore/deleteProduct", operationName = "DeleteProduct")
	public void deleteProduct(@WebParam(name = "Name") final String name) throws WebStoreServiceException {
		try {
			this.storeService.remove(name);
		} catch (final ServiceException e) {
			throw new WebStoreServiceException(e.getMessage(), e.getError());
		}
	}

}
