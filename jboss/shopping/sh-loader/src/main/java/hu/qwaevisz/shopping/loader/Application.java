package hu.qwaevisz.shopping.loader;

import java.sql.SQLException;
import java.util.Random;

public class Application {

	private static final String DATABASE = "shoppingdb";
	private static final String USER = "shopping_user";
	private static final String PASSWORD = "123topSECret321";

	private static final int NUMBER_OF_TRANSACTIONS = 10;

	public static void main(final String[] args) throws ClassNotFoundException, SQLException {

		final Random random = new Random();

		try (final DataService service = new DataService(random, DATABASE, USER, PASSWORD)) {
			final ProductCatalog catalog = service.loadProductCatalog();
			System.out.println(catalog.toString());
			for (int i = 0; i < NUMBER_OF_TRANSACTIONS; i++) {
				System.out.println("New transaction was created (id: " + service.addTransaction(catalog) + ")");
			}
		}
	}

}
