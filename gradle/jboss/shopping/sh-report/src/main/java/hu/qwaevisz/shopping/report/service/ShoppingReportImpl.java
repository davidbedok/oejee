package hu.qwaevisz.shopping.report.service;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import hu.qwaevisz.shopping.report.domain.Transaction;
import hu.qwaevisz.shopping.report.exception.ReportException;
import hu.qwaevisz.shopping.report.util.ReportHelper;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

@Stateless(name = "ShoppingReport", mappedName = "ejb/shoppingReport")
public class ShoppingReportImpl implements ShoppingReport {

	private static final Logger LOGGER = Logger.getLogger(ShoppingReportImpl.class);

	@Resource(name = "shopping-ds")
	private DataSource dataSource;

	@Override
	public List<Transaction> getTransactions() throws ReportException {
		LOGGER.debug("Get all Transactions");
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		try {
			final List<Transaction> transactions = new ArrayList<>();

			connection = this.dataSource.getConnection();
			statement = connection.createStatement();
			final String query = "" + //
					" SELECT " + //
					"  	transaction_id, " + //
					"  	transaction_date, " + //
					" 	transaction_comment, " + //
					" 	(  " + //
					" 		SELECT COUNT(1)  " + //
					" 		FROM item  " + //
					" 		WHERE item_transaction_id = transaction_id  " + //
					" 	) AS number_of_items,  " + //
					" 	(  " + //
					" 		SELECT SUM(item_quantity * product_price)  " + //
					" 		FROM item  " + //
					" 			INNER JOIN product ON ( product_id = item_product_id ) " + //
					" 		WHERE item_transaction_id = transaction_id  " + //
					" 	) AS total_price   " + //
					" FROM transaction ";

			LOGGER.debug("Execute query: " + query);

			rs = statement.executeQuery(query);
			while (rs.next()) {
				final int id = rs.getInt("transaction_id");
				final Date date = rs.getDate("transaction_date");
				final String comment = rs.getString("transaction_comment");
				final int numberOfItems = rs.getInt("number_of_items");
				final int totalPrice = rs.getInt("total_price");

				transactions.add(new Transaction(id, date, comment, numberOfItems, totalPrice));
			}

			return transactions;
		} catch (final SQLException e) {
			LOGGER.error(e, e);
			throw new ReportException(e.getLocalizedMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (final SQLException e) {
				LOGGER.error(e, e);
				throw new ReportException(e.getLocalizedMessage());
			}
		}
	}

	@Override
	public byte[] getTransactionDetailsReport(final long transactionId) throws ReportException {
		final Transaction transaction = this.getTransaction(transactionId);
		try {
			final ReportHelper reportHelper = new ReportHelper();

			final Connection connection = this.dataSource.getConnection();

			final JasperReport report = reportHelper.compile("reports/transaction-details.jrxml");
			final Map<String, Object> params = new HashMap<>();
			params.put("TRANSACTION_ID", transaction.getId());
			params.put("TRANSACTION_DATE", transaction.getDate());
			params.put("TRANSACTION_COMMENT", transaction.getComment());
			final JasperPrint print = reportHelper.fill(report, connection, params);

			return reportHelper.exportToPdf(print);
		} catch (final SQLException e) {
			LOGGER.error(e, e);
			throw new ReportException(e.getLocalizedMessage());
		} catch (final FileNotFoundException e) {
			LOGGER.error(e, e);
			throw new ReportException(e.getLocalizedMessage());
		} catch (final JRException e) {
			LOGGER.error(e, e);
			throw new ReportException(e.getLocalizedMessage());
		}
	}

	private Transaction getTransaction(final long transactionId) throws ReportException {
		LOGGER.debug("Get Transaction by id (" + transactionId + ")");
		Transaction transaction = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {

			connection = this.dataSource.getConnection();
			final String query = "" + //
					" SELECT " + //
					"  	transaction_id, " + //
					"  	transaction_date, " + //
					" 	transaction_comment " + //
					" FROM transaction " + //
					" WHERE ( transaction_id = ? )";
			statement = connection.prepareStatement(query);
			statement.setLong(1, transactionId);

			LOGGER.debug("Execute query: " + query);

			rs = statement.executeQuery();
			while (rs.next()) {
				final int id = rs.getInt("transaction_id");
				final Date date = rs.getDate("transaction_date");
				final String comment = rs.getString("transaction_comment");

				transaction = new Transaction(id, date, comment, 0, 0);
			}

			return transaction;
		} catch (final SQLException e) {
			LOGGER.error(e, e);
			throw new ReportException(e.getLocalizedMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (final SQLException e) {
				LOGGER.error(e, e);
				throw new ReportException(e.getLocalizedMessage());
			}
		}
	}

}
