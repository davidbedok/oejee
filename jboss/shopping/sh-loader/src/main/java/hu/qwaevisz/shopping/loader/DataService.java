package hu.qwaevisz.shopping.loader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import hu.qwaevisz.shopping.loader.domain.ProductCategory;
import hu.qwaevisz.shopping.loader.util.ContentGenerator;

public class DataService implements AutoCloseable {

	private static final String HOST = "localhost";
	private static final int PORT = 5432;

	private final Connection connection;
	private final ContentGenerator generator;

	public DataService(final Random random, final String database, final String user, final String password) throws ClassNotFoundException, SQLException {
		this.generator = new ContentGenerator(random);
		Class.forName("org.postgresql.Driver");
		this.connection = DriverManager.getConnection(this.buildJDBCUrl(database), user, password);
		this.connection.setAutoCommit(true);
	}

	private String buildJDBCUrl(final String database) {
		return "jdbc:postgresql://" + HOST + ":" + PORT + "/" + database;
	}

	public ProductCatalog loadProductCatalog() throws SQLException {
		final ProductCatalog catalog = new ProductCatalog(this.generator.getRandom());
		Statement statement = null;
		ResultSet rs = null;
		try {
			statement = this.connection.createStatement();

			final String query = "SELECT product_id, product_name, productcategory_name, product_price " + //
					"FROM product " + //
					"INNER JOIN productcategory ON ( productcategory_id = product_productcategory_id )";

			rs = statement.executeQuery(query);
			while (rs.next()) {
				final long id = rs.getLong("product_id");
				final String name = rs.getString("product_name");
				final String categoryName = rs.getString("productcategory_name");
				final double price = rs.getDouble("product_price");

				final ProductCategory category = ProductCategory.fromLabel(categoryName);
				catalog.add(id, name, category, price);
			}
		} catch (final SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (statement != null) {
				statement.close();
			}
		}
		return catalog;
	}

	public long addTransaction(final ProductCatalog catalog) throws SQLException {
		long transactionId = -1;
		final String insertTransaction = "INSERT INTO transaction ( transaction_date, transaction_comment ) VALUES (?, ?)";

		final PreparedStatement statement = this.connection.prepareStatement(insertTransaction, Statement.RETURN_GENERATED_KEYS);
		statement.setDate(1, this.generator.generateDate());
		statement.setString(2, this.generator.generateSentence());

		System.out.println(statement.toString());

		statement.executeUpdate();

		try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
			if (generatedKeys.next()) {
				transactionId = generatedKeys.getLong(1);
			}
		}

		final int numberOfItems = this.generator.generateNumberOfItems();

		for (int i = 0; i < numberOfItems; i++) {
			this.addItem(transactionId, catalog);
		}
		return transactionId;
	}

	public void addItem(final long transactionId, final ProductCatalog catalog) throws SQLException {
		final String insertItem = "INSERT INTO item ( item_transaction_id, item_product_id, item_quantity ) VALUES (?, ?, ?)";

		final PreparedStatement statement = this.connection.prepareStatement(insertItem);
		statement.setLong(1, transactionId);
		statement.setLong(2, catalog.getRandomProduct().getId());
		statement.setInt(3, this.generator.generateQuantity());

		System.out.println(statement.toString());

		statement.executeUpdate();
	}

	@Override
	public void close() {
		try {
			this.connection.close();
		} catch (final SQLException e) {
			e.printStackTrace();
		}
	}

}
