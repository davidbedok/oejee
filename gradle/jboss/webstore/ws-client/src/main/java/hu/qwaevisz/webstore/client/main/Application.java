package hu.qwaevisz.webstore.client.main;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import hu.qwaevisz.webstore.client.generated.ProductStub;
import hu.qwaevisz.webstore.client.generated.WebStore;
import hu.qwaevisz.webstore.client.generated.WebStoreService;
import hu.qwaevisz.webstore.client.generated.WebStoreServiceException;

public class Application {

	public static void main(String[] args) throws MalformedURLException {

		URL endpoint = new URL("http://localhost:8080/webstore/WebStoreService?wsdl");

		WebStoreService service = new WebStoreService(endpoint);
		WebStore webStore = service.getWebStorePort();

		try {
			List<ProductStub> products = webStore.listAllProducts();

			for (ProductStub product : products) {
				System.out.println("Product: " + product.getBrand() + ":" + product.getProductName() + " ( price: " + product.getPrice() + " $ )");
			}

		} catch (WebStoreServiceException e) {
			e.printStackTrace();
		}

	}

}
